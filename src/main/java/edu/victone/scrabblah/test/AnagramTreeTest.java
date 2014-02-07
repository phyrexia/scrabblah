package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.game.AnagramTree;
import edu.victone.scrabblah.logic.game.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:33 PM
 */
public class AnagramTreeTest {

    public AnagramTreeTest() {

        try {
            Dictionary.load(new File("sowpods.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final long start = System.currentTimeMillis();
        AnagramTree tree = new AnagramTree("foobar");

        System.out.println("generated " + tree.getAnagrams().size() + " anagrams in " + (System.currentTimeMillis() - start) + "ms");
        for (String s : tree.getAnagrams()) System.out.println(s);
    }

    public static void main(String... args) {
        new AnagramTreeTest();
        System.out.println("completed without blowing up");
    }
}
