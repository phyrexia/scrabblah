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
    //basically a fixed-size doubly-linked-list with an index
    ArrayList<Player> playerList;
    private final int numPlayers;
    private int counter;
    private int index;

    public PlayerList(int numPlayers) {
        this.numPlayers = numPlayers;
        playerList = new ArrayList<Player>(numPlayers);
    }

    public void addPlayer(Player p) {
        if (counter < numPlayers) {
            playerList.add(p);
            counter++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void setIndex(int newIndex) throws ArrayIndexOutOfBoundsException {
        if (newIndex >= numPlayers || newIndex < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        index = newIndex;
    }

    public void incrementIndex() {
        if (index < numPlayers - 1) {
            index++;
        } else {
            index = 0;
        }
    }

    public Player getCurrentPlayer() {
        return playerList.get(index);
    }

    public int size() {
        return playerList.size();
    }

    @Override
    public Iterator<Player> iterator() {
        return playerList.iterator();
    }
}
