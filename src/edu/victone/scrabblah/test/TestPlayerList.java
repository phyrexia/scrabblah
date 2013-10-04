package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.player.PlayerList;
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
        playerList.addPlayer(p);
        p = new HumanPlayer("Bob", 2);
        playerList.addPlayer(p);
        p = new AIPlayer("Computer", 3);
        playerList.addPlayer(p);
        try {
            playerList.addPlayer(p);
            System.out.println("Pass");
        } catch (Exception e) {
            System.out.println("Fail");
        }


        System.out.print("playerListTest 2: ");
        PlayerList pl = new PlayerList(2);
        pl.addPlayer(new HumanPlayer("Victor", 1));
        pl.addPlayer(new HumanPlayer("Rotciv", 2));

        System.out.println(pl.getCurrentPlayer());
        pl.incrementIndex();
        System.out.println(pl.getCurrentPlayer());
    }
}
