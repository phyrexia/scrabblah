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
    int currentNumPlayers;
    int MAX_NUM_PLAYERS;
    int currentPlayerIndex;
    boolean fullyPopulated = false;

    public PlayerList(int numPlayers) {
        MAX_NUM_PLAYERS = numPlayers;
        currentNumPlayers = 0;
        playerList = new ArrayList<Player>(numPlayers);


    }

    public boolean add(Player p) {
        if (currentNumPlayers < MAX_NUM_PLAYERS) {
            playerList.add(p);
            currentNumPlayers++;
            return true;
        } else {
            return false;
        }
    }

    public Player get(int i) {
        return playerList.get(i);
    }

    public void setFirstPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Player getCurrentPlayerIndex() {
        return playerList.get(currentPlayerIndex - 1);
    }

    public Player getNextPlayer() {
        Player nextPlayer = null;
        if (currentPlayerIndex == MAX_NUM_PLAYERS) {
            currentPlayerIndex = 1;
            nextPlayer = playerList.get(0);
        } else {
            nextPlayer = playerList.get(currentPlayerIndex++);
        }
        return nextPlayer;
    }

    public int getMaxNumberPlayers() {
        return MAX_NUM_PLAYERS;
    }


    public int getCurrentNumPlayers() {
        return currentNumPlayers;
    }

    @Override
    public Iterator<Player> iterator() {
        Iterator<Player> iter = playerList.iterator();
        return iter;
    }
}
