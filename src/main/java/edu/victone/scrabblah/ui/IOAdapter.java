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
                if (cpString.charAt(0) != '(' && cpString.charAt(command.length() - 1) != ')') {
                    output.println("ERROR: Invalid input, coords not parenthesized.");
                    return;
                }

                String coordArray[] = cpString.split(",");
                if (coordArray.length != 2) {
                    output.println("ERROR: Invalid input, not enough coords.");
                    return;
                }

                int x = coordArray[0].charAt(0) - 17;

                //debug
                System.out.println(x);

                int y;
                try {
                    y = Integer.parseInt(coordArray[1]);

                } catch (Exception e) {
                    output.println("ERROR: Invalid input, integer parse error.");
                    return;

                }

                //debug
                output.println(y);

                if (x < 0 || x > 14 || y < 0 || y > 15) {
                    output.println("ERROR: Invalid input, coords out of range.");
                    return;
                }

                Coordinate c = new Coordinate(x,y);

                boolean isHorizontal;
                if (components[i].toLowerCase().equals("h")) {
                    isHorizontal = true;
                } else if (components[i].toLowerCase().equals("v")) {
                    isHorizontal = false;
                } else {
                    output.println("ERROR: Invalid input, must specify h/v.");
                    return;
                }

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
            GameBoard newBoard = new GameBoard(gameState.getGameBoard());
            //are all tiles in the player's tile rack (or on the board?)
            Tile t;
            if (w.isHorizontal()) {
                int wx = w.getHead().getX();
                int y = w.getHead().getY();
                for (int x = wx - 1, l = 0; x < wx + w.getWord().length(); x++, l++ ) {

                    if (gameState.getGameBoard().getCellAt(new Coordinate(x,y)).isEmpty()) {
                        t = new Tile(w.getWord().charAt(l));
                        if (gameState.getCurrentPlayer().getTileRack().contains(t)) {
                            //todo: this next
                            //add tile to removelist

                            //ok so
                            //do we want to actually remove the tiles from the board at this point?
                            //we could add references to a list and then remove them after we're verified



                        }
                    }
                }
            }


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