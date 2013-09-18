package edu.victone.scrabblah.logic.player;

import edu.victone.scrabblah.logic.common.Tile;

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
    Random random;

    private List<Tile> rack;

    public TileRack() {
        random = new Random(System.nanoTime());
        rack = new ArrayList<Tile>(MAXSIZE);
    }

    public List<Tile> getRack() {
        return rack;
    }

    public void shuffleRack() {
        Collections.shuffle(rack, random);
    }

    public boolean addTile(Tile t) {
        if (size() < MAXSIZE) {
            rack.add(t);
            return true;
        }
        return false;
    }

    public boolean removeTile(Tile t) {
        if (rack.contains(t)) {
            rack.remove(t);
            return true;
        }
        return false;
    }

    public void dumpRack() { // don't invoke this
        while (rack.size() > 0) {
            System.out.print(rack.remove(0) + " - ");
        }
    }

    public int size() {
        return rack.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (Tile t : rack) {
            s.append(t.getCharacter() + " ");
        }
        return s.toString();
    }
}
