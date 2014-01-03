package edu.victone.scrabblah.logic.game;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:15 PM
 */
public class AnagramTree {
  //Complete, but unoptimized.  And probably due for a rewrite anwyay.
  private Character node;
  private HashSet<AnagramTree> children;
  private AnagramTree parent;
  private HashSet<String> anagrams;

  private String root;

  //1) add current substring to each node
  //2) replace object fields with primitives where possible to decrease JVM overhead
  //2a) look into Trove, Javolution, Guava collections libraries for primitive support

  public AnagramTree(String s) {
    this(null, AnagramTree.stringToSortedCharArray(s), null);
  }

  private static ArrayList<Character> stringToSortedCharArray(String s) {
    ArrayList<Character> charArray = new ArrayList<>(10);
    for (Character c : s.toUpperCase().toCharArray()) {
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
    //actually we want to keep from ever even constructing the child though
    //getSubstring();
    if (node == null && charArray != null) { //head case
      this.node = null;
      StringBuilder rootBuilder = new StringBuilder(10);
      children = new HashSet<>(charArray.size());
      for (Character c : charArray) {
        ArrayList<Character> grandkids = new ArrayList<>(charArray);
        rootBuilder.append(c);
        grandkids.remove(c);
        children.add(new AnagramTree(c, grandkids, this));
      }
      root = rootBuilder.toString();
    } else if (node != null && charArray != null) { //"f, oobar"
      this.node = node;

      hack();

      children = new HashSet<>(charArray.size());
      for (Character c : charArray) {
        //construct a new parent and the grandkids
        ArrayList<Character> grandkids = new ArrayList<>(charArray);
        grandkids.remove(c);
        AnagramTree child;
        child = new AnagramTree(c, grandkids.size() == 0 ? null : grandkids, this);
        children.add(child);
        //}
      }
    } else if (node != null) { //"r"
      this.node = node;
      children = null;
      //if substring not in worddb, don't passUpstream
      hack();

    } else {
      throw new IllegalArgumentException();
    }
    //at every level, should check the trie to ensure the current substring is valid
    //(if a substring is not valid, no sense in going deeper)
  }

  private void hack() {
    String str = getSubstring();
    if (Dictionary.contains(str)) {
      passUpstream(getSubstring());
    }
  }

  private void passUpstream(String string) {
    //send a string up the chain.  but this is terrible and we shouldn't use it this way.
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
