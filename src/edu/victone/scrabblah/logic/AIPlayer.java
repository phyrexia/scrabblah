package edu.victone.scrabblah.logic;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:23 PM
 */

public class AIPlayer extends Player {
    public AIPlayer(String name, int rank) {
        super(name, rank);
        isHuman = false;
    }
}
