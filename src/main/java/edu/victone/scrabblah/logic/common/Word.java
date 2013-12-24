package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:06 PM
 */

public class Word {
    private Coordinate head;
    private String string;
    private int score;
    private boolean orientation;

    public Word(Coordinate head, boolean orientation, String string) {
        this.head = head;
        this.orientation = orientation;
        this.string = string;
    }

    public void setScore(int s) {
            score = s;
    }

    public int getScore() {
        return score;
    }

    public Coordinate getHead() {
        return head;
    }

    public boolean isHorizontal() {
        return orientation;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string + (orientation ? " horizontal at " : " vertical at ") + head;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }

        final Word w = (Word) o;
        return (string.equals(w.string) && orientation == w.orientation && head.equals(w.head));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string, head, orientation);
    }
}