package edu.victone.scrabblah.logic;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:17 PM
 */

public class HumanPlayer extends Player {
    public HumanPlayer(String name, int rank) {
        super(name, rank);
        isHuman = true;
    }
}
