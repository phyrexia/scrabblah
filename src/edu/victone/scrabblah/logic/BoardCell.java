package edu.victone.scrabblah.logic;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/12/13
 * Time: 3:47 PM
 */
public class BoardCell {
    int multiplier;
    boolean affectsWord;
    Tile tile;

    public BoardCell(int multiplier, boolean affectsWord) {
        this.multiplier = multiplier;   //double or triple
        this.affectsWord = affectsWord; //letter or word
        tile = null;
    }

    public boolean placeTile(Tile t) {
        if (tile == null) {
            tile = t;
            return true;
        }
        return false;
    }
}
