package edu.victone.scrabblah.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:19 PM
 * A bag of Tiles
 */


public class TileBag {
    private List<Tile> bag;

    public TileBag() {
        this(true);
    }

    public TileBag(boolean reg) {
        // Place tiles in a bag according to the standard scrabble distribution
        bag = new ArrayList<Tile>();


        add('j', (reg ? 1 : 2)); //
        add('k', (reg ? 1 : 2)); //
        add('q', (reg ? 1 : 2)); //
        add('x', (reg ? 1 : 2)); //
        add('z', (reg ? 1 : 2)); //
        add(' ', (reg ? 2 : 4)); //
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

    private void add(Character c, int frequency) {
        //add a new Tile $frequency number of times.
        //this is only used for initialization.
        for (int i = 1; i <= frequency; i++) {
            bag.add(new Tile(c));
        }
    }

    public void add(Tile t) {
        //add a preconstructed tile to the bag.
        //this is only used when a user swaps tiles.
        bag.add(t);
    }

    public void dumpBag() { // don't call me.  really.
        while (bag.size() > 0) {
            System.out.print(bag.remove(0) + " - ");
        }
    }

    public void shuffleBag() {
        Collections.shuffle(bag, new Random(System.nanoTime()));
    }

    public Tile getTile() { //removes and returns a tile from the list
        if (bag.size() == 0) {
            return null;
        }
        //shuffleBag();
        return bag.remove(0);
    }

    public int size() {
        return bag.size();
    }

    public boolean isEmpty() {
        return (bag.size() == 0);
    }

    @Override
    public String toString() {
        return null;
    }
}
