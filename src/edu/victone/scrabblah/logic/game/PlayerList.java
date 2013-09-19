package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 11:13 PM
 */

public class PlayerList implements Iterable<Player> {
    ArrayList<Player> playerList;
    private final int numPlayers;
    private int counter;
    private int pointer;

    public PlayerList(int numPlayers) {
        this.numPlayers = numPlayers;
        playerList = new ArrayList<Player>(numPlayers);
    }

    public boolean add(Player p) {
        if (counter < numPlayers) {
            playerList.add(p);
            counter++;
            return true;
        } else {
            return false;
        }
    }

    public void incrementPointer() {
        if (pointer < numPlayers - 1) {
            pointer++;
        } else {
            pointer = 0;
        }
    }

    public Player getCurrentPlayer() {
        return playerList.get(pointer);
    }

    @Override
    public Iterator<Player> iterator() {
        return playerList.iterator();
    }
}
