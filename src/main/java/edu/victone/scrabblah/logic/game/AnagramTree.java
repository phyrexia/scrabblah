package edu.victone.scrabblah.logic.game;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:15 PM
 */
public class AnagramTree {
    //Complete, but may be due for a rewrite.
    private AnagramTree parent;

    private HashSet<AnagramTree> children;
    private HashSet<String> anagrams;

    private Character node;
    //private String root;

    //1) add current substring to each node
    //2) replace object fields with primitives where possible to decrease JVM overhead
    //2a) look into Trove, Javolution, Guava collections libraries for primitive support

    public AnagramTree(String s) {
        this(null, AnagramTree.stringToSortedCharArray(s), null);
    }

    private static ArrayList<Character> stringToSortedCharArray(String s) {
        ArrayList<Character> charArray = new ArrayList<>(10);
        for (Character c : s.toLowerCase().toCharArray()) {
            charArray.add(c);
        }
        Collections.sort(charArray);
        return charArray;
    }

    private AnagramTree(Character node, ArrayList<Character> charArray, AnagramTree parent) {
        this.parent = parent;
        if (parent == null) {
            anagrams = new HashSet<>(1024);
        }

        //if getThisSubString() is not parseable into the DB, die
        //actually, we want to keep from ever even constructing the child
        //getSubstring();

        if (node == null && charArray != null) {
            //parent node

            this.node = null;
            //StringBuilder rootBuilder = new StringBuilder(10);
            children = new HashSet<>(charArray.size());
            for (Character c : charArray) {
                ArrayList<Character> grandkids = new ArrayList<>(charArray);
                //rootBuilder.append(c);
                grandkids.remove(c);
                children.add(new AnagramTree(c, grandkids, this));
            }
            //root = rootBuilder.toString();
        } else if (node != null && charArray != null) {
            //"f, oobar" - most frequent node type

            this.node = node;
            //if substring not in worddb, don't passUpstream
            passSubstring();
            children = new HashSet<>(charArray.size());
            for (Character c : charArray) {
                //construct a new parent and the grandkids
                ArrayList<Character> grandkids = new ArrayList<>(charArray);
                grandkids.remove(c);
                AnagramTree child;
                child = new AnagramTree(c, grandkids.size() == 0 ? null : grandkids, this);
                children.add(child);
            }
        } else if (node != null) {
        //"r" - terminal node

            this.node = node;
            children = null;
            //if substring not in worddb, don't passUpstream
            passSubstring();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void passSubstring() {
        //at every level, should check the trie to ensure the current substring is valid
        //(if a substring is not valid, no sense in going deeper)
        //however in lieu of this concept, we can do the following as a partial workaround:
        String str = getSubstring();
        if (Dictionary.contains(str)) {
            passUpstream(getSubstring());
        }
    }

    private void passUpstream(String string) {
        //send a string up the chain.  but this can be fixed when we refactor.
        if (parent == null) {
            anagrams.add(string);
        } else {
            parent.passUpstream(string);
        }
    }

    private String getSubstring() {
        if (parent == null) return "";
        return parent.getSubstring() + getNode();
    }

    public HashSet<String> getAnagrams() {
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