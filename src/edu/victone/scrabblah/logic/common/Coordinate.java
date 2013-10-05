package edu.victone.scrabblah.logic.common;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/17/13
 * Time: 7:13 PM
 */
public class Coordinate {
    private Integer x, y;

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

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Coordinate other = (Coordinate) obj;
        if (x != other.getX() || y != other.getY()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        return hash;
    }
}
