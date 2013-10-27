package edu.victone.scrabblah.ui.console;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Move;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.ui.UserInterface;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 7:32 PM
 */

public class ConsoleUI extends UserInterface {
    Scanner scanner;

    public ConsoleUI() {
        super();

        scanner = new Scanner(System.in);

        printGreeting();

        int numPlayers = queryNumberPlayers();

        setNumberPlayers(numPlayers);

        for (int i = 1; i <= numPlayers; i++) {
            addPlayerToGame(queryPlayerData(i));
        }

        //verifyPlayerData(); //??

        startGame();

        turnLoop(); //loops until game is over

        //DEBUG
        //END DEBUG

        Player winner = gameState.getWinner();

        //display endgame

        printGoodbye();
    }

    @Override
    protected void displayGame() {

    }

    private void printHeader() {
        System.out.println("******************************************************************");
    }

    private void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            //  Handle exception.
            System.out.println("Fatal Error: " + e + "; Terminating.");
            System.exit(1);
        }
    }

    private void printGreeting() {
        printHeader();
        System.out.println("Scrabblah - UAB CS496 - Games Seminar\n(c) Victor Wilson 2013");
        //printHeader();
    }

    private void printGoodbye() {
        Player w = getWinner();
        printHeader();
        if (w != null) {
            System.out.println("Game Over! " + w.getName() + " wins with " + w.getScore() + " points!");
        } else {
            System.out.println("Nobody won.  You must be debugging (or something bad has happened.)");
        }
        printHeader();
    }

    private void printPlayerSummary() {
        printHeader();
        System.out.println("PLAYERS:");
        for (Player p : gameState.getPlayerList()) {
            System.out.println(p);
        }
    }

    @Override
    protected int queryNumberPlayers() {
        int n = -1;
        printHeader();
        do {
            System.out.print("How many players? (2-4): ");
            try {
                n = scanner.nextInt();

            } catch (Exception e) {
                System.out.println("Please enter a number.");
            }
            if (n < 2 || n > 4) {
                System.out.println("Please enter either 2, 3, or 4.");
            }
        } while (n < 2 || n > 4);
        return n;
    }

    @Override
    protected Player queryPlayerData(int rank) {
        printHeader();
        String type = new String();
        do {
            System.out.print("Is Player " + rank + " a human player? (Y/n): ");
            type = scanner.next();
            if (!type.toLowerCase().equals("y") && !type.toLowerCase().equals("n")) {
                System.out.println("Please enter 'y' or 'n'.");
            }
        } while (!type.toLowerCase().equals("y") && !type.toLowerCase().equals("n"));

        String name = new String();
        if (type.toLowerCase().equals("y")) {
            do {
                System.out.print("Player " + rank + " Name: ");
                name = scanner.next();
                if (name.equals("")) {
                    System.out.println("Please enter a name.  Any name.");
                }
            } while (name.equals(""));
        } else {
            name = AIPlayer.playerNames[new Random().nextInt(AIPlayer.playerNames.length)];
        }
        return (type.toLowerCase().equals("y") ? new Player(name, rank) : new AIPlayer(name, rank));
    }

    @Override
    protected Move queryMoveType() {
        String m;
        boolean invalidMoveType;
        do {
            System.out.print("(P)lay a tile\ns(H)uffle Rack\n(S)wap tiles\n(R)ecall Tiles\n(E)nd Turn\n(Q)uit\nEnter your move: ");
            m = scanner.next().toLowerCase();
            invalidMoveType = !m.equals("p") &&
                    !m.equals("h") &&
                    !m.equals("s") &&
                    !m.equals("r") &&
                    !m.equals("e") &&
                    !m.equals("q");
            if (invalidMoveType) {
                System.out.println("Invalid entry.  Please try again.");
            }
        } while (invalidMoveType);

        switch (m.charAt(0)) {
            case 'p':
                return Move.PLAY;
            case 'h':
                return Move.SHUFFLE;
            case 's':
                return Move.SWAP;
            case 'r':
                return Move.RESIGN;
            case 'e':
                return Move.ENDTURN;
            case 'q':
                String input = null;
                do {
                    System.out.print("Are you sure you want to exit? (y/n): ");
                    input = scanner.next().toLowerCase();
                    if (input.equals("y")) {
                        System.out.println("Terminating.");
                        System.exit(0);
                    }
                } while (!input.equals("y") && !input.equals("n"));
                return Move.DONOTHING;

            default:
                //throw new Exception("Bad move input.");
                return null;
        }
    }

    @Override //GUI might not be able to override
    protected void playTurn(Player currentPlayer) {
        clearConsole();
        printPlayerSummary();
        System.out.print(gameState.getGameBoard());
        System.out.println("Current Player: " + currentPlayer);
        System.out.println(currentPlayer.getTileRack());
        if (gameState.errorPresent()) {
            System.out.println(gameState.getErrorMessage());
        }

        switch (queryMoveType()) {
            case PLAY:
                queryPlay(currentPlayer);
                break;
            case SWAP:
                querySwap(currentPlayer);
                break;
            case RESIGN:
                queryResignation(currentPlayer);
                break;
            case SHUFFLE:
                currentPlayer.getTileRack().shuffleRack();
                break;
            case ENDTURN:
                endTurn();
                break;
            case DONOTHING:
                break;
        }
    }

    @Override
    protected void querySwap(Player currentPlayer) {
        String input = null;
        Character c = '$';
        Tile t = null;
        ArrayList<Tile> out = new ArrayList<Tile>();

        do {
            System.out.print("Enter the tile to swap(# to finish): ");
            input = scanner.next().toLowerCase();
            if (input.length() > 1) {
                System.out.println("Please enter a single letter.");
                continue;
            }

            c = input.charAt(0);
            t = new Tile(c);
            if (!currentPlayer.getTileRack().contains(t) && !c.equals('#')) {
                System.out.println("Your rack does not contain " + c.toString() + ".  Enter a valid letter.");
                continue;
            }
            currentPlayer.getTileRack().removeTile(t);
            if (!c.equals('#')) out.add(t);
        } while (!c.equals('#'));

        currentPlayer.getTileRack().addTiles(gameState.getTileBag().swapTiles(out));
    }

    @Override
    protected void queryPlay(Player currentPlayer) {
        printHeader();
        System.out.println(currentPlayer.getTileRack());
        String input;
        Tile t = null;
        do {
            System.out.print("Enter the letter (# to finish): ");
            input = scanner.next().toLowerCase();
            if (input.length() > 1) {
                System.out.println("Please enter a single letter.");
                continue;
            }
            Character c = input.charAt(0);
            t = new Tile(c);


            if (!currentPlayer.getTileRack().contains(t)) {
                System.out.println("Your rack does not contain " + c.toString() + ".  Enter a valid letter.");
            }
        } while (!currentPlayer.getTileRack().contains(t));

        Integer x = null, y = null;
        boolean validX = false, validY = false;

        do {
            System.out.print("Enter the horizontal coordinate: ");
            input = scanner.next().toUpperCase();
            x = (int) input.charAt(0) - 65;
            if (input.length() == 1 && x >= 0 && x <= 14) {
                validX = true;
            } else {
                System.out.println("Please enter a character between A and O.");
            }
        } while (!validX);

        do {
            System.out.print("Enter the vertical coordinate: ");
            input = scanner.next().toUpperCase();
            y = (int) input.charAt(0) - 65;
            if (input.length() == 1 && y >= 0 && y <= 14) {
                validY = true;
            } else {
                System.out.println("Please enter a character between A and O.");
            }
        } while (!validY);

        Coordinate coord = new Coordinate(x, y);

        if (gameState.getGameBoard().getCellAt(coord).isEmpty()) {
            System.out.println(currentPlayer.getTileRack().removeTile(t));
            gameState.placeTile(t, coord);
        } else {
            System.out.println("Please choose an unoccupied cell.");
        }

    }

    @Override
    public void queryResignation(Player p) {
        //are you sure you want to queryResignation?
    }
}
