package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.game.PlayerList;
import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.HumanPlayer;
import edu.victone.scrabblah.logic.player.Player;

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

        printPlayerSummary();

        //verifyPlayerData();

        startGame();

        //turnLoop(); //loops until game is over

        //Player winner = gameState.getWinner();

        //display endgame

        //printGoodbye();
    }

    private void printHeader() {
        System.out.println("*************************************");

    }

    private void printGreeting() {
        printHeader();
        System.out.println("Scrabblah - UAB CS466 - Games Seminar\n(c) Victor Wilson 2013");
        printHeader();
    }

    private void printGoodbye() {
        Player w = getWinner();
        printHeader();
        System.out.println("Game Over! " + w.getName() + " wins with " + w.getScore() + " points!");
        printHeader();
    }

    private void printPlayerSummary() {
        printHeader();

        for (Player p : gameState.getPlayerList()) {
            //System.out.println("Player " + p.getRank() + ": " + p.getName() + "\tPoints: " + p.getScore());
            System.out.println(p);
            printHeader();
        }
    }

    @Override
    protected int queryNumberPlayers() {
        int n;
        do {
            System.out.print("How many players? (2-4): ");
            n = scanner.nextInt();
            if (n < 2 || n > 4) {
                System.out.println("Please enter either 2, 3, or 4.");
            }
        } while (n < 2 || n > 4);
        return n;
    }

    @Override
    protected Player queryPlayerData(int rank) {
        String type = "";
        do {
            System.out.print("Is Player " + rank + " a human player? (Y/n): ");
            type = scanner.next();
            if (!type.toLowerCase().equals("y") && !type.toLowerCase().equals("n")) {
                System.out.println("Please enter 'y' or 'n'.");
            }
        } while (!type.toLowerCase().equals("y") && !type.toLowerCase().equals("n"));

        String name = "";
        if (type.toLowerCase().equals("y")) {
            do {
                System.out.print("Player " + rank + " Name: ");
                name = scanner.next();
                if (name.equals("")) {
                    System.out.println("Please enter a name.  Any name.");
                }
            } while (name.equals(""));
        } else {
            name = UserInterface.playerNames[new Random().nextInt(3)];
        }
        return (type.toLowerCase().equals("y") ? new HumanPlayer(name, rank) : new AIPlayer(name, rank));
    }

    @Override
    protected boolean startGame() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void turnLoop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void playTurn(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pass(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void swap(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean play(Player player, Word word, Coordinate coord, boolean orientation) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resign(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void displayGame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

   /* Scanner scanner;

    public ConsoleUI() {
        super();

        scanner = new Scanner(System.in);

        initGame();
        startGame();
        playGame();
        endGame();
    }

    @Override
    protected void initGame() {
        printGreeting();

        int numPlayers = queryNumberPlayers();

        setNumberPlayers(numPlayers);

        for (int i = 1; i <= numPlayers; i++) {
            addPlayerToGame(queryPlayerData(i));
        }
    }

    @Override
    protected boolean startGame() {
        return false;
    }

    @Override
    protected void playGame() {

    }

    @Override
    protected void endGame() {

    }

    private void printGreeting() {
        System.out.println("*************************************");
        System.out.println("Scrabblah - UAB CS466 - Games Seminar\n(c) Victor Wilson 2013");
        System.out.println("*************************************");
    }

    @Override
    protected int queryNumberPlayers() {
        int n;
        do {
            System.out.print("How many players? (2-4): ");
            n = scanner.nextInt();
            if (n < 2 || n > 4) {
                System.out.println("Please enter either 2, 3, or 4.");
            }
        } while (n < 2 || n > 4);
        return n;
    }

    @Override
    protected Player queryPlayerData(int rank) {
        String type = "";
        do {
            System.out.print("Is Player " + rank + " a human player? (y/n): ");
            type = scanner.next();
            if (!type.toLowerCase().equals("y") && !type.toLowerCase().equals("n")) {
                System.out.println("Please enter 'y' or 'n'.");
            }
        } while (!type.toLowerCase().equals("y") && !type.toLowerCase().equals("n"));

        String name = "";
        if (type.toLowerCase().equals("y")) {
            do {
                System.out.print("Player " + rank + " Name: ");
                name = scanner.next();
                if (name.equals("")) {
                    System.out.println("Please enter a name.  Any name.");
                }
            } while (name.equals(""));
        } else {
            name = UserInterface.playerNames[new Random().nextInt(3)];
        }

        return (type.toLowerCase().equals("y") ? new HumanPlayer(name, rank) : new AIPlayer(name, rank));
    }

    @Override
    protected void displayGame() {
    }

    */
}
