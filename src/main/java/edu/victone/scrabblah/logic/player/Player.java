package edu.victone.scrabblah.logic.player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:10 PM
 */

public class Player {
    protected String name;
    protected int score = 0;

    protected TileRack tileRack;

    protected boolean resigned = false;

    public Player(String name) {
        this.name = name;
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
        return name + " (human) - Score: " + score;
    }
}
