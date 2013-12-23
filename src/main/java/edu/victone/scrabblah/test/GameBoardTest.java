package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.game.GameBoard;

/**
 * author: vwilson
 * date: 12/23/13
 */

public class GameBoardTest {


    public static void main(String[] args) {
        GameBoard a = new GameBoard();
        a.getCellAt(8,8).setTile((new Tile('c')));
        System.out.println(a);

        GameBoard b = new GameBoard(a);
        System.out.println(b);
    }
}
