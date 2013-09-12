package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.Player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 7:40 PM
 */

public abstract class UserInterface {
    public IOAdapter adapter;

    public static String[] playerNames = {"Charles", "Alan", "Neumann"};

    public UserInterface() {
        adapter = new IOAdapter();
    }

    abstract protected void initGame();

    abstract protected boolean startGame();

    abstract protected void playGame();

    abstract protected void endGame();

    abstract protected int queryNumberPlayers();

    abstract protected Player queryPlayerData(int rank);

    abstract protected void displayGame();
}
