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
    //TODO: the following array should be a collection so duplicate names can't occur
    public static String[] playerNames = {"Charles B.", "Bill G.", "Steve J.", "Steve W.", "Alan T.", "John V.N.", "Bob H.", "Ken S.", "John J."};

    public UserInterface() {
        gameState = new GameState();
    }

    //Game Precondition Methods

    protected abstract int queryNumberPlayers(); //get num players from user

    protected abstract Player queryPlayerData(int rank); //get player data from user

    protected final void setNumberPlayers(int numPlayers) {
        gameState.setNumberPlayers(numPlayers);
    }

    protected final boolean addPlayerToGame(Player player) {
        return gameState.addPlayer(player);
    }

    protected final boolean startGame() {
        return gameState.startGame();
    }

    //Gameplay Methods - these change state

    protected final void turnLoop() {
        //display board, current player data, prompt for move
        while (!gameState.isGameOver()) { //while the game is not over...
            //it's up to the gameState/gameEngine to determine
            //if a valid move has been made, and to increment the
            //player pointer.
            playTurn(gameState.getCurrentPlayer());
        }
    }

    protected abstract Move queryMoveType(); //implement this too

    protected abstract void playTurn(Player p); //implement this in your derived *UI classes

    abstract protected void pass(Player p); //player passes a turn

    abstract protected void swap(Player p); //player swaps some tiles

    protected final void shuffle(Player p) {
        //player shuffles rack

        p.getTileRack().shuffleRack();
    }

    //abstract protected void recall(Player p);

    abstract protected void resign(Player p); //player quits

    //Game State Getters

    public boolean endTurn() {
        return gameState.endTurn();
    }

    public String getGameStatus() {
        //return gameState.getStatus();
        return "game status.";
    }

    protected final int getNumberPlayers() {
        return gameState.getNumberPlayers();
    }

    protected final Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }

    protected final boolean isGameOver() {
        return gameState.isGameOver();
    }

    // abstract protected void displayGame();

    protected final Player getWinner() {
        //this method might be unnecessary
        return gameState.getWinner();
    }

}
