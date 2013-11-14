package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.game.GameState;
import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * author: vwilson
 * date: 10/30/13
 */

public class IOAdapter {

    private GameState gameState;
    private UserInterface ui;

    public IOAdapter() { /*...*/ }

    public void setGameState() {

    }

    public void setUi(UserInterface ui) {
        this.ui = ui;
    }

    public boolean listen() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            parse(scanner.next());
            //System.out.println(gameState);
        }
    }

    private void parse(String command) {
        /*
        so the properly formed expressions are:

        (new)
        (add {human/cpu} [$PlayerName])
        (start)

        (play {h/v} $word (headx,heady))
          or would we rather have
        (play $tile (x,y)) + (turn) when done
        (pass)
        (swap t i l e s)
        (resign)
        */

        command = command.trim();

        //is it valid parenthesized s-exp?
        if (command.charAt(0) != '(' && command.charAt(command.length()) != ')') {
            System.out.println("ERROR: Invalid input.");
            return;
        }

        String[] components = command.substring(1, command.length() - 1).split(" ");
        int i = 0;
        switch (components[i++]) {
            case "new":
                if (i == components.length) {
                    newGame();
                } else {
                    System.out.println("ERROR: Invalid syntax.");
                }
                break;
            case "add": //add a player to the list.
                Player p;
                if (components[i++].toLowerCase().equals("human")) {
                    String playerName;
                    try {
                        playerName = components[i++];
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("ERROR: Human players must have specified names.");
                        return;
                    }
                    p = new Player(playerName);
                } else {
                    p = new AIPlayer(AIPlayer.getRandomName());
                }
                gameState.addPlayer(p);
                break;
            case "start":
                break;
            case "play":
                //parse rest of stmt
                Coordinate c = new Coordinate(0, 0);
                Word w = new Word(c, true, "word");
                play(w);
                break;
            case "pass":
                gameState.pass();
                break;
            case "swap":
                //todo: implement
                ArrayList<Tile> toSwap = new ArrayList<>(7);
                for (int j = i; j < components.length; j++) {

                }
                if (swap(null)) {
                } else {
                    System.out.println("ERROR: Unable to swap.  Not enough remaining tiles.");
                }
                break;
            case "resign":
                gameState.resign();
                break;
            case "quit":
                System.out.println("Terminating.");
                System.exit(0);
                break;
            default:
                System.out.println("ERROR: Invalid input.");
                break;
        }
        char[] commandArray = command.substring(1, command.length() - 1).toCharArray();

        System.out.println(/* msg */);
    }

    private void newGame() {
        //todo:
        gameState = new GameState();

    }

    private boolean play(Word w) {
        //place each tile of w on the board
        //check for validity
        //if valid end turn, return true
        //if not...
        return false;
    }

    private boolean swap(ArrayList<Tile> tilesToSwap) {
        if (gameState.getNumberRemainingTiles() < tilesToSwap.size()) {
            return false;
        }
        gameState.swapTiles(tilesToSwap);
        return true;
    }
}