package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.game.AnagramTree;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:33 PM
 */
public class AnagramTreeNodeTest {

    public AnagramTreeNodeTest() {
        final long start = System.currentTimeMillis();
        AnagramTree tree = new AnagramTree("foobar");
        //before pruning has been implemented,
        // 720 anagrams in 25ms (on a good day apparently?)

        for (String s : tree.getAnagrams()) System.out.println(s);
        System.out.println("generated " + tree.getAnagrams().size() + " anagrams in " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void main(String[] args) {
        new AnagramTreeNodeTest();
        System.out.println("completed without blowing up");
    }
}
