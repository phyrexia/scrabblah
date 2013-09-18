package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.HumanPlayer;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.logic.game.PlayerList;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:02 PM
 */

public class TestDriver {
    public static void main(String[] args) {

        argTest(args);

        playerListTest();

    }

    private static void playerListTest() {
        System.out.print("playerListTest 1: ");
        PlayerList playerList = new PlayerList(3);
        Player p = new HumanPlayer("Ace", 1);
        playerList.add(p);
        p = new HumanPlayer("Bob", 2);
        playerList.add(p);
        p = new AIPlayer("Computer", 3);
        playerList.add(p);
        boolean test = playerList.add(p);
        if (test) {
            System.out.println("Fail.");
        } else {
            System.out.println("Pass.");
        }

        //System.out.print("playerListTest 2: ");
        System.out.print("playerListTest 2: ");
        PlayerList pl = new PlayerList(2);
        pl.add(new HumanPlayer("Victor", 1));
        pl.add(new HumanPlayer("Becca", 2));

        System.out.println(pl.get(0));
        System.out.println(pl.get(1));
        //playerList.setCurrentPlayerIndex(3);


    }

    private static void argTest(String[] args) {
        System.out.print("argTest: ");
        if (args.length > 1 && args[1].equals("-g")) {
            System.out.println("Fail.");
        } else {
            //console mode
            System.out.println("Pass.");
        }
    }

    private static void playerListTest2() {

    }
}
