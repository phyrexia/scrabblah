package edu.victone.scrabblah.logic;

import edu.victone.scrabblah.logic.TileRack;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:10 PM
 */

public abstract class Player {
    private String name;
    private int score;
    private int rank;
    private boolean isHuman;

    private TileRack rack;

    @Override
    public String toString() {
        return null;
    }
}
