package edu.victone.scrabblah.logic.common;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/17/13
 * Time: 7:13 PM
 */

public class Coordinate {
    private final Integer x, y;

    public Coordinate(int x, int y) {
        if ((x < 0) || (x > 14)) {
            throw new IllegalArgumentException("Illegal X Coordinate value: " + x);
        } else if ((y < 0) || (y > 14)) {
            throw new IllegalArgumentException("Illegal Y Coordinate value: " + y);
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

    public String print() {
        Character xChar = (char) (x + 48);
        Character yChar = (char) (y + 48);
        return ("(" + xChar + ',' + yChar + ')');
    }

    @Override
    public String toString() {
        return '(' + x + ", " + y + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        final Coordinate other = (Coordinate) o;
        return !(!x.equals(other.x) || !y.equals(other.y));
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    public static void main(String... args) {
        Coordinate a = new Coordinate(4, 4);
        Coordinate b = new Coordinate(4, 4);

        System.out.println(a.equals(b) ? "pass" : "fail");
    }
}
