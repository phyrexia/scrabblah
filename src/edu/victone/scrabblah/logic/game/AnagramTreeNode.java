package edu.victone.scrabblah.logic.game;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:15 PM
 */
public class AnagramTreeNode {
    private Character node;
    private StringBuilder accumulator;
    private String root;
    private AnagramTreeNode parent;
    private ArrayList<AnagramTreeNode> children;
    private boolean divisible;

    public AnagramTreeNode(String s) {
        this(null, AnagramTreeNode.stringToSortedCharArray(s));
    }

    private static ArrayList<Character> stringToSortedCharArray(String s) {
        ArrayList<Character> charArray = new ArrayList<Character>();
        for (Character c : s.toCharArray()) {
            charArray.add(c);
        }
        return charArray;
    }

    private AnagramTreeNode(Character node, ArrayList<Character> charArray) {
        if (node == null && charArray != null) { //head case
            this.node = null;
            StringBuilder rootBuilder = new StringBuilder();
            children = new ArrayList<AnagramTreeNode>();
            for (Character c : charArray) {
                ArrayList<Character> grandkids = new ArrayList<Character>(charArray);
                rootBuilder.append(c);
                grandkids.remove(c);
                children.add(new AnagramTreeNode(c, grandkids));
            }
            root = rootBuilder.toString();
        } else if (node != null && charArray != null) { //"f, oobar"
            this.node = node;
            children = new ArrayList<AnagramTreeNode>();
            for (Character c : charArray) {
                //construct a new parent and the grandkids
                ArrayList<Character> grandkids = new ArrayList<Character>(charArray);
                grandkids.remove(c);
                AnagramTreeNode child;
                child = new AnagramTreeNode(c, grandkids.size() == 0 ? null : grandkids);
                children.add(child);
            }
        } else if (node != null && charArray == null) { //"r"
            this.node = node;
            children = null;

        } else {
            throw new IllegalArgumentException();
        }
        //at every level, should check the database to ensure the current substring is valid
        //(if a substring is not valid, no sense in going deeper)
    }

    public String getValue() {
        return node == null ? root : node.toString();
    }

    public boolean hasChildren() {
        return children != null;
    }

    public ArrayList<AnagramTreeNode> getChildren() {
        return children;
    }
}
