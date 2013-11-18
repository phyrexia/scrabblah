package edu.victone.scrabblah.logic.player;

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
    ArrayList<Player> activePlayers;
    private int numberPlayers;
    private int index;

    public PlayerList() {
        playerList = new ArrayList<Player>(4);
        activePlayers = new ArrayList<Player>(4);
    }

    public void addPlayer(Player p) {
        if (numberPlayers < 4) {
            playerList.add(p);
            activePlayers.add(p);
            numberPlayers++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void setIndex(int newIndex) throws ArrayIndexOutOfBoundsException {
        if (newIndex >= numberPlayers || newIndex < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        index = newIndex;
    }

    public void incrementIndex() {
        if (index < numberPlayers - 1) {
            index++;
        } else {
            index = 0;
        }
        if (getCurrentPlayer().hasResigned()) {
            incrementIndex();
        }
    }

    public Player getCurrentPlayer() {
        return playerList.get(index);
    }

    public ArrayList<Player> getActivePlayers() {
        //returns updated list
        for (Player p : activePlayers) {
            if (p.hasResigned()) {
                activePlayers.remove(p);
            }
        }
        return activePlayers;
    }

    public int size() {
        return numberPlayers;
    }

    @Override
    public Iterator<Player> iterator() {
        return playerList.iterator();
    }
}
