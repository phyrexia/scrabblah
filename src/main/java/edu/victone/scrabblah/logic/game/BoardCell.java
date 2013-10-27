package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/12/13
 * Time: 3:47 PM
 */
public class BoardCell {
    private int multiplier;
    private boolean isWordMultiplier;
    private Tile tile;
    private boolean lock;

    public BoardCell(int multiplier, boolean isWordMultiplier) {
        this.multiplier = multiplier;   //double or triple
        this.isWordMultiplier = isWordMultiplier; //letter or word
        tile = null;
    }

    public boolean setTile(Tile t) {
        if (isEmpty()) {
            tile = t;
            return true;
        }
        return false;
    }

    public Tile recallTile() {
        if (lock) {
            return null;
        } else {
            Tile retVal = tile;
            tile = null;
            return retVal;
        }
    }

    public void lock() {
        if (tile != null)
            lock = true;
    }

    public boolean isLocked() {
        return lock;
    }

    public boolean isEmpty() {
        return (tile == null);
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean isWordMultiplier() {
        return isWordMultiplier;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public String toString() {
        String output = "";
        switch (multiplier) {
            case 1:
                output = "  ";
                break;
            case 2:
                output = (isWordMultiplier ? "DW" : "DL");
                break;
            case 3:
                output = (isWordMultiplier ? "TW" : "TL");
                break;
        }
        return output;
    }
}
