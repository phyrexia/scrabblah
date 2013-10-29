package edu.victone.scrabblah.logic.game;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/8/13
 * Time: 3:29 AM
 */
public class PatriciaTrie {
    HashMap<Character, PatriciaTreeNode> trees;
    int size;

    public PatriciaTrie() {
        trees = new HashMap<Character, PatriciaTreeNode>(52, .5f);
        for (int i = 65; i < 91; i++) {
            trees.put((char) i, new PatriciaTreeNode((char) i));
        }
        size = 0;
    }

    public void add(CharSequence cs) {
        trees.get(cs.charAt(0)).add(cs);
        //dump word to that hashmap
        size++;
    }

    public boolean contains(CharSequence cs) {
        return trees.get(cs.charAt(0)).contains(cs);
    }

    public int size() {
        return size;
    }
}
