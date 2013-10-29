package edu.victone.scrabblah.logic.game;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/10/13
 * Time: 12:39 AM
 */
public class SubstringTree {
    //the substring tree for a single letter of the alphabet

    //this is really a trie and implementations exist.
    //may use ardverk's patricia trie
    //or may roll my own

    Character head; //might not really be neccessary

    Set<SubstringTree> children;

    String currentSubstring;

    int depth;

    //head constructor
    public SubstringTree(char c) {
        children = new HashSet<SubstringTree>();
        depth = 0;
        head = c;
    }

    //leaf constructor
    public SubstringTree(CharSequence cs, int depth) {
        currentSubstring = cs.subSequence(0,depth).toString(); ;

    }

    public void add(CharSequence cs) {
       // if (children.contains(cs.s))
        children.add(new SubstringTree(cs, 0));

    }

    public boolean contains(CharSequence cs) {
        return false;
    }
}
