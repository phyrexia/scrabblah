package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.TileBag;
import edu.victone.scrabblah.logic.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:00 PM
 */

//The "MODEL" of our Model-View-Controller

public class GameState {
    private GameBoard gameBoard;
    private GameEngine gameEngine;
    private PlayerList playerList;
    private TileBag tileBag;

    private boolean initialized = false;
    private int turnCounter = 0;
    private Player winner;

    public GameState() {
        gameEngine = new GameEngine();
        gameBoard = new GameBoard();

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setNumberPlayers(int numPlayers) {
        playerList = new PlayerList(numPlayers);
    }

    public int getNumberPlayers() {
        return playerList.getMaxNumberPlayers();
    }

    public boolean addPlayer(Player p) {
        return playerList.add(p);
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public boolean ready() {
        if (playerList != null) {
            return true;
        }
        return false;
    }

    public void startGame() {

    }

    public boolean isGameOver() {
        //TODO: implement me
        return false;
    }

    @Override
    public String toString() {
        //  TODO: implement me
        return null;
    }

    public Player getCurrentPlayer() {
        //TODO: implement me
        return null;
    }

    public Player getWinner() {
        return winner;
    }
}
