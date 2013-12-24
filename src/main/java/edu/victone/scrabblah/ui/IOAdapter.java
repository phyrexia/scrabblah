package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.game.Dictionary;
import edu.victone.scrabblah.logic.game.GameState;
import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * author: vwilson
 * date: 10/30/13
 */

public class IOAdapter {
    private static final Pattern SPLIT_SPACE = Pattern.compile(" ");
    private static final Pattern SPLIT_COMMA = Pattern.compile(",");

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

        //noinspection InfiniteLoopStatement
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
                output.println(gameState.getStatusMessage());
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

        String[] components = SPLIT_SPACE.split(command.substring(1, command.length() - 1));
        int i = 0;
        switch (components[i++]) {
            case "new":
                if (components.length == 1) {
                    gameState = new GameState();
                    try {
                        Dictionary.load(new File("sowpods.txt"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    output.println("New Game initializing.");
                } else {
                    output.println("ERROR: Invalid syntax.");
                }
                break;
            case "add": //add a player to the list.
                if (gameStateIsNull()) return;

                Player p;
                if (components.length == 1) {
                    p = new AIPlayer();
                } else {
                    StringBuilder playerName = new StringBuilder(16);
                    for (int j = i; j < components.length; j++) {
                        playerName.append(components[j]);
                        if (j < components.length - 1) {
                            playerName.append(' ');
                        }
                    }
                    p = new Player(playerName.toString());
                }

                if (gameState.addPlayer(p)) {

                    output.println("Added " + p.getName() + " to the game.");
                }       else {
                    output.println("ERROR: Could not add player.");

                }
                break;
            case "start":
                if (gameStateIsNull()) return;
                start();
                break;
            case "play":
                if (gameStateIsNull()) return;

                //are coordinates valid parenthesized s-exp? (ish)
                String cpString = components[i++];
                System.out.println(cpString);
                if (cpString.charAt(0) != '(' || cpString.charAt(cpString.length() - 1) != ')') {
                    output.println("ERROR: Invalid input, coords not parenthesized.");
                    return;
                }

                //parse coordinates
                cpString = cpString.substring(1, cpString.length() - 1);
                String coordArray[] = SPLIT_COMMA.split(cpString);
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

                play(w);
                break;
            case "pass":
                if (gameStateIsNull()) return;
                output.println(gameState.getCurrentPlayer().getName() + " passing.");
                gameState.pass();
                break;
            case "shuffle":
                gameState.getCurrentPlayer().getTileRack().shuffleRack();
                break;
            case "swap":
                if (gameStateIsNull()) return;
                ArrayList<Tile> toSwap = new ArrayList<>(7);
                for (int j = i; j < components.length; j++) {
                    toSwap.add(new Tile(components[j].charAt(0)));
                }
                swap(toSwap);
                break;
            case "resign":
                if (gameStateIsNull()) return;
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

    private boolean gameStateIsNull() {
        if (gameState == null) {
            output.println("ERROR: No game yet.");
            return true;
        }
        return false;
    }

    private void displayHelp() {
        output.println("Available Commands:");
        output.println("\t(new)\t\t\t\t\t\tNew Game\n" +
                "\t(add)\t\t\t\t\t\tAdd an AI player\n" +
                "\t(add $playerName)\t\t\tAdd a named Human player\n" +
                "\t(start)\t\t\t\t\t\tStart the Game\n" +
                "\t(play (x,y) {h/v} $word)\tPlay $word at (x, y), oriented h or v\n" +
                "\t(shuffle)\t\t\t\t\tShuffle your tile rack\n" +
                "\t(pass)\t\t\t\t\t\tPass current turn\n" +
                "\t(swap t i l e s)\t\t\tSwap the listed tiles\n" +
                "\t(resign)\t\t\t\t\tResign the game\n" +
                "\t(quit)\t\t\t\t\t\tExit the program\n" +
                "\t(help)\t\t\t\t\t\tPrint this message");
    }

    private void start() {
        gameState.startGame();
    }

    private void play(Word w) {
        gameState.playWord(w);
    }

    private void swap(ArrayList<Tile> tilesToSwap) {
        gameState.swapTiles(tilesToSwap);
    }
}