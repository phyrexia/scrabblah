package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.game.AnagramTreeNode;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/7/13
 * Time: 8:33 PM
 */
public class AnagramTreeNodeTest {

    public AnagramTreeNodeTest() {
        String s = "FOOBAR";
        AnagramTreeNode tree = new AnagramTreeNode(s);
        System.out.println(tree.getChildren().size());
        for (AnagramTreeNode atn : tree.getChildren()) {
            System.out.println(atn.getValue());
        }
    }

    public void traverseTree(AnagramTreeNode atn) {
        //System.out.println()
    }

    public static void main(String[] args) {
        new AnagramTreeNodeTest();
        System.out.println("completed without blowing up");
    }
}
