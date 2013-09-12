package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.AIPlayer;
import edu.victone.scrabblah.logic.HumanPlayer;
import edu.victone.scrabblah.logic.Player;

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

        initGame();
        startGame();
        playGame();
        endGame();
    }

    @Override
    protected void initGame() {
        printGreeting();

        int numPlayers = queryNumberPlayers();

        adapter.setNumberPlayers(numPlayers);

        Player p;
        for (int i = 1; i <= numPlayers; i++) {
            p = queryPlayerData(i);
            adapter.addPlayer(p);
        }
    }

    @Override
    protected void startGame() {

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
            scanner.next();
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
}
