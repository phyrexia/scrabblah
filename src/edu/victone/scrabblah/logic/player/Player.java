package edu.victone.scrabblah.logic.player;

import edu.victone.scrabblah.logic.common.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:10 PM
 */

public class Player {
    protected String name;
    protected int rank;
    protected int score = 0;

    protected TileRack tileRack;

    protected boolean resigned = false;

    public Player(String name, int rank) {
        this.name = name;
        this.rank = rank;
        tileRack = new TileRack();
    }

    public void resign() {
        resigned = true;
    }

    public boolean hasResigned() {
        return resigned;
    }

    public TileRack getTileRack() {
        return tileRack;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int delta) {
        score += delta;
    }

    @Override
    public String toString() {
        return "P" + rank + ": " + name + " (human) - Score: " + score;
    }
}
