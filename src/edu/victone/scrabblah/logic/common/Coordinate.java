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
        if ((x < 0) || (x > 14)) {
            throw new NullPointerException("Illegal X Coordinate value: " + x);
        } else if ((y < 0) || (y > 14)) {
            throw new NullPointerException("Illegal Y Coordinate value: " + y);
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

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
