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
    private boolean affectsWord;
    private Tile tile;
    private boolean lock;

    public BoardCell(int multiplier, boolean affectsWord) {
        this.multiplier = multiplier;   //double or triple
        this.affectsWord = affectsWord; //letter or word
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

    public boolean isEmpty() {
        return (tile == null);
    }

    @Override
    public String toString() {
        String output = "";
        switch (multiplier) {
            case 1:
                output = "  ";
                break;
            case 2:
                output = (affectsWord ? "DW" : "DL");
                break;
            case 3:
                output = (affectsWord ? "TW" : "TL");
                break;
        }
        return output;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean affectsWord() {
        return affectsWord;
    }

    public Tile getTile() {
        return tile;
    }
}
