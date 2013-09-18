package edu.victone.scrabblah.logic.common;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/17/13
 * Time: 7:13 PM
 */
public class Coordinate {
    private int x, y;

    public Coordinate(int x, int y) {
        if ((x < 0) || (x > 14) || (y < 0) || (y > 14)) {
            throw new NullPointerException("Illegal Coordinate value");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
