package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.GameEngine;
import edu.victone.scrabblah.logic.Player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 3:57 PM
 */
public abstract class UserInterfaceAdapter {
    GameEngine game;

    public UserInterfaceAdapter(GameEngine game) {
        this.game = game; //expose public gameengine methods to adapter
    }

    //Game Precondition Methods
    abstract void setNumberPlayers(int numPlayers);

    abstract void setPlayerData(int playerRank, Object playerData); //fuuuck

    abstract boolean startGame();

    //Gameplay Methods
    abstract void pass(Player p);

    abstract void swap(Player p);

    abstract void play(Player p);

    abstract void resign(Player p);

    //Game State Getters
    abstract int getScore(Player p);


}
