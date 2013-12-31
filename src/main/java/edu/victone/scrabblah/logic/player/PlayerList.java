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
    //ArrayList<Player> activePlayers;
    private int numberPlayers;
    private int index;

    public PlayerList() {
        playerList = new ArrayList<>(4);
    }

    public boolean addPlayer(Player p) {
        if (numberPlayers < 4) {
            playerList.add(p);
         //   activePlayers.add(p);
            numberPlayers++;
            return true;
        } else {
            return false;
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
//      Iterator iter = playerList.iterator();
//      while (iter.hasNext()) {
//        Player p = (Player) iter.next();
//
//      }
      ArrayList<Player> activePlayers = new ArrayList<>(4);
        for (Player p : playerList) {
            if (!p.hasResigned()) {
                activePlayers.add(p);
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
