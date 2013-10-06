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

    public UserInterface() {
        gameState = new GameState();
    }

    protected abstract void displayGame();

    protected abstract int queryNumberPlayers(); //get num players from user

    protected abstract Player queryPlayerData(int rank); //get player data from user

    protected abstract Move queryMoveType(); //implement this too

    protected abstract void playTurn(Player p); //implement this in your derived *UI classes

    protected abstract void pass(Player p); //player passes a turn

    protected abstract void swap(Player p); //player swaps some tiles

    protected abstract void querySwap(Player currentPlayer);

    protected abstract void queryPlay(Player currentPlayer);

    protected abstract void resign(Player p); //player quits

    protected final void setNumberPlayers(int numPlayers) {
        gameState.setNumberPlayers(numPlayers);
    }

    protected final void addPlayerToGame(Player player) {
        gameState.addPlayer(player);
    }

    protected final boolean startGame() {
        return gameState.startGame();
    }

    protected final void turnLoop() {
        while (!gameState.isGameOver()) { //while the game is not over...
            playTurn(gameState.getCurrentPlayer());
        }
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

    protected final Player getWinner() {
        return gameState.getWinner();
    }

    protected final void shuffle(Player p) {
        p.getTileRack().shuffleRack();
    }

    protected final boolean endTurn() {
        return gameState.endTurn();
    }
}
