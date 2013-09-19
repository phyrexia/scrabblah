package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Move;
import edu.victone.scrabblah.logic.game.GameState;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.logic.common.Word;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 7:40 PM
 */

public abstract class UserInterface {
    protected GameState gameState;
    //TODO: the following array should be a collection so there are no duplicate names
    public static String[] playerNames = {"Charles B.", "Alan T.", "John V.N."};

    public UserInterface() {
        gameState = new GameState();
    }

    //Game Precondition Methods

    abstract protected int queryNumberPlayers(); //get num players from user

    abstract protected Player queryPlayerData(int rank); //get player data from user

    protected void setNumberPlayers(int numPlayers) {
        gameState.setNumberPlayers(numPlayers);
    }

    protected boolean addPlayerToGame(Player player) {
        return gameState.addPlayer(player);
    }

    protected boolean startGame() {
        return gameState.startGame();
    }

    //Gameplay Methods - these change state

    protected void turnLoop() {
        //display board, current player data, prompt for move
        while (!gameState.isGameOver()) {
            playTurn(gameState.getNextPlayer());
        }
    }

    abstract protected void playTurn(Player p); //implement this in your derived *UI classes

    protected abstract Move queryMove();

    abstract protected void pass(Player p); //player passes a turn

    abstract protected void swap(Player p); //player swaps some tiles

    abstract protected boolean play(Player player, Word word, Coordinate coord, boolean orientation);
    //player attempts to play word at point, aligned orientation-wise.
    //if it's a valid move, makes the move, and returns true
    //if it's not a valid move, doesn't make the move, and returns false

    abstract protected void resign(Player p); //player quits

    //Game State Getters

    public int getNumberPlayers() {
        return gameState.getNumberPlayers();
    }

    public Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }

    protected boolean isGameOver() {
        return gameState.isGameOver();
    }

    abstract protected void displayGame();

    protected Player getWinner() {
        //TODO: implement me
        return null;
    }

}
