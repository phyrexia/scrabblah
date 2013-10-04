package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.game.PlayerList;
import edu.victone.scrabblah.logic.player.AIPlayer;
import edu.victone.scrabblah.logic.player.HumanPlayer;
import edu.victone.scrabblah.logic.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/3/13
 * Time: 10:36 PM
 */
public class TestPlayerList {
    public static void main(String args[]) {
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

        System.out.print("playerListTest 2: ");
        PlayerList pl = new PlayerList(2);
        pl.add(new HumanPlayer("Victor", 1));
        pl.add(new HumanPlayer("Rotciv", 2));

        System.out.println(pl.getCurrentPlayer());
        pl.incrementPointer();
        System.out.println(pl.getCurrentPlayer());
    }
}
