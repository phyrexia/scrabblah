package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:06 PM
 */

public class Word {
    public static final boolean HORIZONTAL = true;
    public static final boolean VERTICAL = false;

    private String word;
    private boolean orientation;
    private Coordinate head;
    private int score;
    private boolean locked = false;

    public Word(Coordinate head, boolean orientation, String word) {
        this.head = head;
        this.orientation = orientation;
        this.word = word;
    }

    public void setScore(int s) {
        if (locked) {
            throw new UnsupportedOperationException();
        } else {
            score = s;
            lock();
        }
    }

    public boolean lock() {
        return (!locked && (locked = true));
    }

    public Coordinate getHead() {
        return head;
    }

    public boolean getOrientation() {
        return orientation;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(word);
        sb.append(orientation ? " horizontal at " : " vertical at ");
        sb.append(head.print() + "; " + head);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }

        final Word w = (Word) o;
        if (getWord() == w.getWord() &&
                getOrientation() == w.getOrientation() &&
                getHead() == w.getHead()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(word, head, orientation);
    }
}
