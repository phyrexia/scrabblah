package edu.victone.scrabblah.io;

import edu.victone.scrabblah.logic.game.GameState;

import java.util.Scanner;

/**
 * author: vwilson
 * date: 10/28/13
 */

public class InputArbiter {
    private Scanner scanner;
    private GameState gameState;

    public InputArbiter() {
        gameState = new GameState();
        scanner = new Scanner(System.in);
        loop();
    }

    public void loop() {
        while (true) {
            String command = scanner.next();

            //parse command

            //send command to gamestate

            //print response
        }
    }

    public static void main(String... args) {
        new InputArbiter();
    }
}
