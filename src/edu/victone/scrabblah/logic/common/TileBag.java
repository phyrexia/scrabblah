package edu.victone.scrabblah.logic.common;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:19 PM
 * A tileBag of Tiles
 */

public class TileBag {
    private List<Tile> tileBag;
    private Random random;

    public TileBag() {
        this(true);
    }

    public TileBag(boolean reg) {
        random = new Random(System.nanoTime());

        // Place tiles in a tileBag according to the standard scrabble distribution
        tileBag = new ArrayList<Tile>();

        add('j', (reg ? 1 : 2)); //
        add('k', (reg ? 1 : 2)); //
        add('q', (reg ? 1 : 2)); //
        add('x', (reg ? 1 : 2)); //
        add('z', (reg ? 1 : 2)); //
        //add('_', (reg ? 2 : 4)); //no blanks for now.
        add('b', (reg ? 2 : 4)); //
        add('c', (reg ? 2 : 6)); //
        add('f', (reg ? 2 : 4)); //
        add('h', (reg ? 2 : 5)); //
        add('m', (reg ? 2 : 6)); //
        add('v', (reg ? 2 : 3)); //
        add('w', (reg ? 2 : 4)); //
        add('y', (reg ? 2 : 4)); //
        add('p', (reg ? 2 : 4)); //
        add('g', (reg ? 3 : 5)); //
        add('s', (reg ? 4 : 10)); //
        add('u', (reg ? 4 : 7)); //
        add('d', (reg ? 4 : 8)); //
        add('l', (reg ? 4 : 7)); //
        add('n', (reg ? 6 : 13)); //
        add('t', (reg ? 6 : 15)); //
        add('r', (reg ? 6 : 13)); //
        add('o', (reg ? 8 : 15)); //
        add('a', (reg ? 9 : 16)); //
        add('i', (reg ? 9 : 13)); //
        add('e', (reg ? 12 : 24)); //

        shuffleBag();
    }

    public ArrayList<Tile> swapTiles(ArrayList<Tile> tilesToSwap) {
        int n = tilesToSwap.size();

        for (Tile t : tilesToSwap) {
            add(t);
        }

        shuffleBag();

        return getTiles(n);
    }

    private void add(Character c, int frequency) {
        //add a new Tile $frequency number of times.
        //this is only used for initialization.
        for (int i = 1; i <= frequency; i++) {
            tileBag.add(new Tile(c));
        }
    }

    private void add(Tile t) {
        //add a preconstructed tile to the tileBag.
        //this is only used when a user swaps tiles.
        tileBag.add(t);
        shuffleBag();
    }

    public void dumpBag() { // don't call me.  really.
        while (tileBag.size() > 0) {
            System.out.print(tileBag.remove(0) + " - ");
        }
    }

    private void shuffleBag() {
        Collections.shuffle(tileBag, random);
    }

    public ArrayList<Tile> getTiles(int numTiles) {
        ArrayList<Tile> tileList = new ArrayList<Tile>(numTiles);
        for (int i = 1; i <= numTiles; i++) {
            tileList.add(getTile());
        }
        return tileList;
    }

    public Tile getTile() { //removes and returns a tile from the list
        if (tileBag.size() == 0) {
            return null;
        }
        return tileBag.remove(0);
    }

    public int size() {
        return tileBag.size();
    }

    public boolean isEmpty() {
        return (tileBag.size() == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < tileBag.size(); i++) {
            sb.append(tileBag.get(i).getCharacter());
            if (i != tileBag.size() - 1)
                sb.append(", ");
        }

        return sb.toString();
    }
}
