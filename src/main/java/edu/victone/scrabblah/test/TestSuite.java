package edu.victone.scrabblah.test;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:02 PM
 */

public class TestSuite {
    public static void main(String[] args) {
        argTest(args);

        playerListTest();

    }

    private static void playerListTest() {


    }

    private static void argTest(String[] args) {
        System.out.print("argTest: ");
        if (args.length > 1 && args[1].equals("-g")) {
            System.out.println("Fail.");
        } else {
            //console mode
            System.out.println("Pass.");
        }
    }
}
