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
 * randomly populated bag of STiles
 */


public class TileBag {
    List<Tile> bag;

    public TileBag() {
        // randomly generate Tiles according to distribution and put them in a
        // bag. Then shuffle the bag.
        bag = new ArrayList<Tile>();

        add('j', 1); //
        add('k', 1); //
        add('q', 1); //
        add('x', 1); //
        add('z', 1); //
        add(' ', 2); //
        add('b', 2); //
        add('c', 2); //
        add('f', 2); //
        add('h', 2); //
        add('m', 2); //
        add('v', 2); //
        add('w', 2); //
        add('y', 2); //
        add('p', 2); //
        add('g', 3); //
        add('s', 4); //
        add('u', 4); //
        add('d', 4); //
        add('l', 4); //
        add('n', 6); //
        add('t', 6); //
        add('r', 6); //
        add('o', 8); //
        add('a', 9); //
        add('i', 9); //
        add('e', 12); //

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
}
