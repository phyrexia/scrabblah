package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.*;

import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 3:57 PM
 */

public class IOAdapter {
    private GameEngine game;
    private TileBag tileBag;

    public IOAdapter() {
        game = new GameEngine();
        tileBag = new TileBag();
    }

    //Game Precondition Methods
    public void setNumberPlayers(int numPlayers) {


    }

    public boolean addPlayer(Player player) {
        return false;
    }

    public boolean startGame() {
        return false;
    }

    //Gameplay Methods - these change state
    public void pass(Player p) {

    }

    public void swap(Player p) {

    }

    public boolean play(Player player, Word word, Point point, boolean orientation) {
        //player attempts to play word at point, aligned orientation-wise.
        //if it's a valid move, makes the move, and returns true
        //if it's not a valid move, doesn't make the move, and returns false
        return false;
    }

    public void resign(Player p) {

    }

    //***********************************************************
    //Game State Getters
    //***********************************************************

    public String getBoardState() {
        return game.getBoard().toString();
    }

    public GameState getGameState() {
        return game.getGameState();
    }

    public int getScore(Player p) {
        return -1;
    }

    public Player getCurrentPlayer() {
        return null;
    }

    public int getNumberPlayers() {
        return -1;
    }

    public List<Player> getPlayerList() {
        return null;
    }

    public boolean gameOver() {
        return false;
    }

    public Player getVictoriousPlayer() {
        return null;
    }
}

//***********************************************************
//
//***********************************************************


