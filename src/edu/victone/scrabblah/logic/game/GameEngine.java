package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Word;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:40 PM
 */
public class GameEngine {
    public static void initialize() {
        try {
            File f = new File("sowpods.txt");
            Dictionary.loadDictionary(f);
        } catch (FileNotFoundException e) {
            System.err.println("Fatal Error: Dictionary File Not Found.");
            System.exit(1);
        }
    }

    public static int computeScore(GameBoard oldBoard, GameBoard currentBoard) {
        return 4;
    }

    private static boolean isLegalPlay(GameBoard oldBoard, GameBoard currentBoard) {
        return true;
    }

//    public static boolean isLegalWordPlacement(GameBoard b, Word w, int turn) {
//        if (!Dictionary.contains(w.toString())) {
//            return false;
//        }
//
//        if (turn == 1) {
//            if (false /* word doesn't touch center cell */) {
//                return false;
//            }
//        }
//
//        //TODO: find other words created by placement of w on b
//
//        //TODO: additional verification
//
//        return true;
//    }
}
