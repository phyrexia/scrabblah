package edu.victone.scrabblah.test;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:02 PM
 */
public class TestDriver {
    public static void main(String[] args) {

        if (args.length > 1 && args[1].equals("-g")) {
            System.out.println("This should not appear.");
        } else {
            //console mode
            System.out.println("This should appear.");
        }
    }
}
