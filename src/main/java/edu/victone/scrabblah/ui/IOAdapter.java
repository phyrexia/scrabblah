package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.game.GameBoard;
import edu.victone.scrabblah.logic.game.GameEngine;
import edu.victone.scrabblah.logic.game.GameState;
import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.Player;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * author: vwilson
 * date: 10/30/13
 */

public class IOAdapter {

    //todo: all strings -> static consts or proper modern java idiom

    private GameState gameState;

    private InputStream input;
    private PrintStream output;


    public IOAdapter(InputStream input, PrintStream output) {
        this.input = input;
        this.output = output;
        /*...*/
    }

    public boolean listen() {
        Scanner scanner = new Scanner(input);

        while (true) {
            output.print("$");
            parse(scanner.nextLine());
            display();
        }
    }

    private void display() {
        if (gameState != null && gameState.isActive()) {
            output.println(gameState.getGameBoard());
            output.println(gameState.getCurrentPlayer());
            output.println(gameState.getCurrentPlayer().getTileRack());
            if (gameState.errorPresent()) {
                output.println(gameState.getErrorMessage());
            }
        }
    }

    private void parse(String command) {
        command = command.trim();

        if (command.length() < 4) {
            output.println("ERROR: Huh?");
            return;
        }

        //is it valid parenthesized s-exp? (ish)
        if (command.charAt(0) != '(' && command.charAt(command.length() - 1) != ')') {
            output.println("(ERROR: Invalid input.)");
            return;
        }

        String[] components = command.substring(1, command.length() - 1).split(" ");
        int i = 0;
        switch (components[i++]) {
            case "new":
                if (components.length == 1) {
                    gameState = new GameState();
                    output.println("New Game initializing.");
                } else {
                    output.println("ERROR: Invalid syntax.");
                }
                break;
            case "add": //add a player to the list.
                if (gameState == null) {
                    output.println("ERROR: No game yet.");
                    return;
                }

                if (gameState.getNumberPlayers() == 4) {
                    output.println("ERROR: Max players already added to game.");
                    return;
                }

                Player p;
                if (components.length == 1) {
                    p = new AIPlayer();
                } else {
                    StringBuilder playerName = new StringBuilder();
                    for (int j = i; j < components.length; j++) {
                        playerName.append(components[j]);
                        playerName.append(" ");
                    }
                    p = new Player(playerName.toString());
                }

                gameState.addPlayer(p);
                output.println("Added " + p.getName() + " to the game.");
                break;
            case "start":
                if (gameState == null) {
                    output.println("ERROR: No game yet.");
                    return;
                }
                start();
                break;
            case "play":
                if (gameState == null) {
                    output.println("ERROR: No game yet.");
                    return;
                }

                //are coordinates valid parenthesized s-exp? (ish)
                String cpString = components[i++];
                System.out.println(cpString);
                if (cpString.charAt(0) != '(' || cpString.charAt(cpString.length() - 1) != ')') {
                    output.println("ERROR: Invalid input, coords not parenthesized.");
                    return;
                }

                //parse coordinates
                cpString = cpString.substring(1, cpString.length() - 1);
                String coordArray[] = cpString.split(",");
                if (coordArray.length != 2) {
                    output.println("ERROR: Invalid input, not enough coords.");
                    return;
                }

                int x = coordArray[0].toLowerCase().charAt(0) - 97;

                int y;
                try {
                    y = Integer.parseInt(coordArray[1]) - 1;

                } catch (Exception e) {
                    output.println("ERROR: Invalid input, integer parse error.");
                    return;

                }

                if (x < 0 || x > 14 || y < 0 || y > 14) {
                    output.println("ERROR: Invalid input, coords out of range.");
                    return;
                }

                //parse orientation
                boolean isHorizontal;
                if (components[i].toLowerCase().equals("h")) {
                    isHorizontal = true;
                } else if (components[i].toLowerCase().equals("v")) {
                    isHorizontal = false;
                } else {
                    output.println("ERROR: Invalid input, must specify h/v.");
                    return;
                }

                Coordinate c = new Coordinate(x,y);

                String word = components[++i];
                Word w = new Word(c, isHorizontal, word);

                output.println("Attempting to play " + w);
                play(w);
                break;
            case "pass":
                if (gameState == null) {
                    output.println("ERROR: No game yet.");
                    return;
                }
                output.println(gameState.getCurrentPlayer().getName() + " passing.");
                gameState.pass();
                break;
            case "swap":
                if (gameState == null) {
                    output.println("ERROR: No game yet.");
                    return;
                }
                //todo: implement
                ArrayList<Tile> toSwap = new ArrayList<>(7);
                for (int j = i; j < components.length; j++) {

                }
                if (swap(null)) {
                } else {
                    output.println("ERROR: Unable to swap.  Not enough remaining tiles.");
                }
                break;
            case "resign":
                if (gameState == null) {
                    output.println("ERROR: No game yet.");
                    return;
                }
                output.println(gameState.getCurrentPlayer().getName() + " resigning.");
                gameState.resign();
                break;
            case "help":
                displayHelp();
                break;
            case "quit":
                output.println("Terminating.");
                System.exit(0);
                break;
            default:
                output.println("ERROR: Invalid input.");
                break;
        }
    }

    private void displayHelp() {
        output.println("Available Commands:");
        output.println("\t(new)\tNew Game\n" +
                "\t(add)\tAdd an AI player\n" +
                "\t(add $playerName)\tAdd a named Human player\n" +
                "\t(start)\tStart the Game\n" +
                "\t(play ($headx,$heady) {h/v} $word)\tPlay $word at ($headx, $heady), oriented h or v\n" +
                "\t(pass)\tPass current turn\n" +
                "\t(swap t i l e s)\tSwap the listed tiles\n" +
                "\t(resign)\n" +
                "\t(help)\tPrint this message");

    }

    private void start() {
        if (gameState.getNumberPlayers() > 1) {
            gameState.startGame();
        } else {
            output.println("ERROR: Not enough players.  Add " + (2 - gameState.getNumberPlayers()) + " to " +
                    (4 - gameState.getNumberPlayers()) + " players.");
        }
    }

    private void play(Word w) {
        //place each tile of w on the board
        //check for validity
        //if valid end turn, return true
        //if not...

        if (GameEngine.dictionary.contains(w.getWord())) {
            ArrayList<Tile> tilesToRemove = new ArrayList<>() ;
            GameBoard newBoard = new GameBoard(gameState.getGameBoard());
            //newBoard = gameState.getGameBoard();
            //are all tiles in the player's tile rack (or on the board?)
            Tile t;
            if (w.isHorizontal()) {
                int wx = w.getHead().getX();
                int y = w.getHead().getY();
                for (int x = wx, ptr = 0; x < wx + w.getWord().length(); x++, ptr++ ) {
                    t = new Tile(w.getWord().charAt(ptr));
                    Coordinate c = new Coordinate(x,y);
                    if (gameState.getGameBoard().getCellAt(c).isEmpty()) {
                        if (gameState.getCurrentPlayer().getTileRack().contains(t)) {
                            tilesToRemove.add(t);
                            newBoard.getCellAt(c).setTile(t);
                        } else {
                            //fail: this tile is not in the rack
                            output.println("ERROR: Tile " + t + " not in rack");
                            return;
                        }
                    } else if (gameState.getGameBoard().getCellAt(c).getTile().equals(t)) {
                        //same tile, doesn't have to be in rack, so do nothing...

                    } else {
                        //fail, can't play a tile on another tile.
                        output.println("ERROR: Tile " + t + " doesn't fit");
                        return;
                    }
                }
            } else {
                int x = w.getHead().getX();
                int wy = w.getHead().getY();
                for (int y = wy, ptr = 0; y < wy + w.getWord().length(); y++, ptr++ ) {
                    t = new Tile(w.getWord().charAt(ptr));
                    Coordinate c = new Coordinate(x,y);
                    if (gameState.getGameBoard().getCellAt(c).isEmpty()) {
                        if (gameState.getCurrentPlayer().getTileRack().contains(t)) {
                            tilesToRemove.add(t);
                            newBoard.getCellAt(c).setTile(t);
                        } else {
                            //fail: this tile is not in the rack
                            output.println("ERROR: Tile " + t + " not in rack");
                            return;

                        }
                    } else if (gameState.getGameBoard().getCellAt(c).getTile().equals(t)) {
                        //same tile, doesn't have to be in rack, so do nothing...

                    } else {
                        //fail, can't play a tile on another tile.
                        output.println("ERROR: Tile " + t + " doesn't fit");
                        return;
                    }
                }
            }
            //if we made it this far we're good
            if (GameEngine.isLegalGameBoard(newBoard)) {
                gameState.pushGameBoard(newBoard);
            } else {
                return;
            }
            for (Tile tile : tilesToRemove) {
                gameState.getCurrentPlayer().getTileRack().removeTile(tile);
            }
            gameState.endTurn();
        } else {
            output.println("ERROR: " + w.getWord() + " is not in the dictionary.");
        }
    }

    private boolean swap(ArrayList<Tile> tilesToSwap) {
        if (gameState.getNumberRemainingTiles() < tilesToSwap.size()) {
            return false;
        }
        gameState.swapTiles(tilesToSwap);
        return true;
    }

    private void printError(int errorCode) {
        //todo: convert

    }
}