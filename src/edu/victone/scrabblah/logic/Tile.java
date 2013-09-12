package edu.victone.scrabblah.logic;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:11 PM
 */
public class Tile {
    private Character letter;
    private int value;

    public Tile(Character c) {
        letter = c;
        value = getValue(c);
    }

    public Tile(char c) {
        this(new Character(c));
    }

    public Character getCharacter() {
        return letter;
    }

    private int getValue(Character c) {
        int val;
        switch (c.charValue()) {
            case 'e':
            case 'a':
            case 'i':
            case 'o':
            case 'n':
            case 'r':
            case 't':
            case 'l':
            case 's':
            case 'u':
                val = 1;
                break;
            case 'd':
            case 'g':
                val = 2;
                break;
            case 'b':
            case 'c':
            case 'm':
            case 'p':
                val = 3;
                break;
            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y':
                val = 4;
                break;
            case 'k':
                val = 5;
                break;
            case 'j':
            case 'x':
                val = 8;
                break;
            case 'q':
            case 'z':
                val = 10;
                break;
            case ' ':
                val = 0;
                break;
            default:
                val = -1;
                break;
        }
        return val;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return letter + ": " + value;
    }
}
