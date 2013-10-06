package edu.victone.scrabblah.logic.common;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:06 PM
 */

public class Word {
    public static final boolean HORIZONTAL = true;
    public static final boolean VERTICAL = false;

    String word;
    boolean orientation;
    Coordinate head;
    int score;
    boolean locked = false;

    public Word(Coordinate head, boolean orientation, String word) {
        this.head = head;
        this.orientation = orientation;
        this.word = word;
    }

    public void setScore(int s) {
        if (locked) {
            throw new UnsupportedOperationException();
        } else score = s;
    }

    public boolean lock() {
        return (!locked && (locked = true));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(word);
        sb.append(orientation ? " horizontal at " : " vertical at ");
        sb.append(head.print() + "; " + head);
        return sb.toString();
    }
}
