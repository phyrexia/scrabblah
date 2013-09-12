package edu.victone.scrabblah.logic;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 11:13 PM
 */

public class PlayerList {
    ArrayList<Player> playerList;
    int numPlayers;
    int currentPlayerIndex;

    public PlayerList(int numPlayers) {
        this.numPlayers = numPlayers;
        playerList = new ArrayList<Player>(numPlayers);
    }

    public boolean addPlayer(Player p) {
        if (playerList.size() < numPlayers) {
            playerList.add(p.getRank() - 1, p);
            return true;
        } else {
            return false;
        }
    }

    public Player getNextPlayer() {
        Player nextPlayer = null;
        if (currentPlayerIndex == numPlayers) {
            currentPlayerIndex = 1;
            nextPlayer = playerList.get(0);
        } else {
            nextPlayer = playerList.get(currentPlayerIndex++);
        }
        return nextPlayer;
    }

    public Player getCurrentPlayerIndex() {
        return playerList.get(currentPlayerIndex - 1);
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}
