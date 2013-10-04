package edu.victone.scrabblah.logic.player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:23 PM
 */

public class AIPlayer extends Player {
    public static String[] playerNames = {"Charles B.", "Bill G.", "Steve J.", "Steve W.", "Alan T.", "John V.N.", "Bob H.", "Ken S.", "John J."};

    public AIPlayer(String name, int rank) {
        super(name, rank);
        isHuman = false;
    }

    @Override
    public boolean playWord() {
        return false;
    }

    @Override
    public boolean swap() {
        return false;
    }

    @Override
    public boolean pass() {
        return false;
    }

    @Override
    public void resign() {
    }
}
