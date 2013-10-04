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

    public Word(Coordinate head, boolean orientation, String word) {
        this.head = head;
        this.orientation = orientation;
        this.word = word;
    }

    @Override
    public String toString() {
        //todo: implement Word.toString()
        return null;
    }
}
