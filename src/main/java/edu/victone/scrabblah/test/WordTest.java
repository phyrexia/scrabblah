package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.common.*;

import java.util.*;

/**
 * Created by vwilson on 1/2/14!
 */
public class WordTest {
  public static void main(String... args) {
    Coordinate a = new Coordinate(7,7);
    Coordinate b = new Coordinate(7,7);

    Word w = new Word(a, true, "cat");
    Word w2 = new Word(b, true, "cat");
    Word w3 = new Word(new Coordinate(7,6), false, "bat");
    Word w4 = new Word(new Coordinate(7,6), false, "bat");

    if (w.equals(w2)) {
      System.out.println("pass");

    } else {
      System.out.println("fail");
    }

    if (w3.equals(w4)) {
      System.out.println("pass");

    } else {
      System.out.println("fail");
    }

    ArrayList<Word> more = new ArrayList<>();
    ArrayList<Word> less = new ArrayList<>();

    less.add(w);

    more.add(w2);
    more.add(w3);

    System.out.println(less);
    System.out.println(more);

    more.removeAll(less);

    System.out.println(more);

  }
}
