package edu.victone.scrabblah.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:15 PM
 */

public class TileRack {
    static final int MAXSIZE = 7;

    private List<Tile> rack;
    private TileBag tileBag;

    public TileRack(TileBag tileBag) {
        this.tileBag = tileBag;

        rack = new ArrayList<Tile>();
        refillRack();
    }

    public List<Tile> getTiles() {
        return rack;
    }

    public void shuffle() {
        Collections.shuffle(rack, new Random(System.nanoTime()));
    }

    public void refillRack() {
        while (rack.size() < MAXSIZE) {
            tileBag.shuffleBag();
            Tile tile = tileBag.getTile();

            if (tile != null) {
                rack.add(tile);
            } else
                break;
        }
    }

    public void shuffleRack() {
        Collections.shuffle(rack, new Random(System.nanoTime()));
    }

    public boolean removeTile(Tile st) {
        if (rack.contains(st)) {
            rack.remove(st);
            return true;
        }
        return false;
    }

    public void swap(List<Tile> tilesToSwap) {
        for (Tile st : tilesToSwap) {
            if ( removeTile(st) ) {
                bagTile(st);
            }
        }
        refillRack();
    }

    private void bagTile(Tile st) {
        tileBag.add(st);
    }

    public int size() {
        return rack.size();
    }

    public void dumpRack() { // don't invoke this
        while (rack.size() > 0) {
            System.out.print(rack.remove(0) + " - ");
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (Tile st : rack) {
            s += st.getCharacter() + " ";
        }
        return s;

    }

}
