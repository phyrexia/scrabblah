package edu.victone.scrabblah.logic.game;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/8/13
 * Time: 3:29 AM
 */
public class SubstringDB {
    HashMap<Character, SubstringTree> trees;
    int size;

    public SubstringDB() {
        trees = new HashMap<Character, SubstringTree>(52, .5f);
        for (int i = 65; i < 91; i++) {
            trees.put(new Character((char) i), new SubstringTree((char) i));
        }
        size = 0;
    }

    private static char getFirstLetter(CharSequence cs) {
        return cs.charAt(0);
    }

    public void add(CharSequence cs) {
        trees.get(getFirstLetter(cs)).add(cs);        //dump word to that hashmap
        size++;
    }

    public boolean contains(CharSequence cs) {
        return trees.get(getFirstLetter(cs)).contains(cs);
    }

    public int size() {
        return size;
    }
}
