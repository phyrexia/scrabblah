package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.common.*;
import edu.victone.scrabblah.logic.game.GameBoard;

/**
 * author: vwilson
 * date: 12/23/13
 */

public class GameBoardTest {

  public static void main(String... args) {
    GameBoard a = new GameBoard();
    Coordinate c = new Coordinate(8, 8);
    Tile t = new Tile('c');

    a.getCellAt(c).setTile(t);
    //System.out.println(a);

    //GameBoard b = new GameBoard(a);
    //System.out.println(b);
  }
}
