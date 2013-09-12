package edu.victone.scrabblah.logic;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:40 PM
 */
public class GameEngine {
    List<Player> playerList;
    Board board;

    public GameEngine() {

    }

    public boolean startGame() {
        //if all of the things a game needs to start are present, start the game
        return false;
    }

    public GameState getGameState() {
        return null;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return null;
    }

    public void setNumberPlayers(int numPlayers) {
    }
}
