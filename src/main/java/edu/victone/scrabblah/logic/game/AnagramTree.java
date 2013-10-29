package edu.victone.scrabblah.logic.game;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:15 PM
 */
public class AnagramTree {
    //Complete, but unoptimized.
    private Character node;
    private HashSet<AnagramTree> children;
    private AnagramTree parent;
    private ArrayList<String> anagrams;

    private String root;

    //1) add current substring to each node
    //2) replace object fields with primitives where possible to decrease JVM overhead
    //2a) look into Trove, Javolution, Guava collections libraries for primitive support

    public AnagramTree(String s) {
        this(null, AnagramTree.stringToSortedCharArray(s), null);
    }

    private static ArrayList<Character> stringToSortedCharArray(String s) {
        ArrayList<Character> charArray = new ArrayList<Character>();
        for (Character c : s.toUpperCase().toCharArray()) {
            charArray.add(c);
        }
        Collections.sort(charArray);
        return charArray;
    }

    private AnagramTree(Character node, ArrayList<Character> charArray, AnagramTree parent) {
        this.parent = parent;
        if (parent == null) {
            System.out.println("this parent is null");
            anagrams = new ArrayList<String>();
        }
        //if getThisSubString() is not parseable into the DB, die
        //actually we want to keep from ever even constructing the child though
        //getSubstring();
        if (node == null && charArray != null) { //head case
            this.node = null;
            StringBuilder rootBuilder = new StringBuilder();
            children = new HashSet<AnagramTree>();
            for (Character c : charArray) {
                ArrayList<Character> grandkids = new ArrayList<Character>(charArray);
                rootBuilder.append(c);
                grandkids.remove(c);
                children.add(new AnagramTree(c, grandkids, this));
            }
            root = rootBuilder.toString();
        } else if (node != null && charArray != null) { //"f, oobar"
            this.node = node;
            //if getSubString()) {
            children = new HashSet<AnagramTree>();
            for (Character c : charArray) {
                //construct a new parent and the grandkids
                ArrayList<Character> grandkids = new ArrayList<Character>(charArray);
                grandkids.remove(c);
                AnagramTree child;
                child = new AnagramTree(c, grandkids.size() == 0 ? null : grandkids, this);
                children.add(child);
            //}
            }
        } else if (node != null && charArray == null) { //"r"
            this.node = node;
            children = null;
            //if substring not in worddb, don't passUpstream
            passUpstream(getSubstring());

        } else {
            throw new IllegalArgumentException();
        }
        //at every level, should check the database to ensure the current substring is valid
        //(if a substring is not valid, no sense in going deeper)
    }

    private String getSubstring() {
        if (parent == null) return "";
        //debuggery
        //String s = parent.getSubstring() + getNode();
        //System.out.println(this + ": " + s);
        return parent.getSubstring() + getNode();
    }

    private void passUpstream(String string) {
        if (parent == null) {
            anagrams.add(string);
        } else {
            parent.passUpstream(string);
        }
    }

    public ArrayList<String> getAnagrams() {
        return anagrams;
    }

    public String getNode() {
        return node == null ? "" : node.toString();
    }

    public boolean hasChildren() {
        return children != null;
    }

    public HashSet<AnagramTree> getChildren() {
        return children;
    }
}
