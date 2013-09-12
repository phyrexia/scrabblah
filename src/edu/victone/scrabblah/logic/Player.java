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
    private int rank;
    protected boolean isHuman;

    private TileRack tileRack;

    private int score = 0;

    public Player(String name, int rank) {
        this.name = name;
        this.rank = rank;
        // tileRack = new TileRack();
    }

    @Override
    public String toString() {
        return "Player " + rank + ": " + name + " (" + (isHuman ? "human" : "machine") + ") - Score: " + score;
    }

    public int getRank() {
        return rank;
    }
}
