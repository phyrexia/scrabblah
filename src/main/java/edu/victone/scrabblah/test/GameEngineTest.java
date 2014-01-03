package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.common.*;
import edu.victone.scrabblah.logic.game.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/3/13
 * Time: 8:24 PM
 */
public class GameEngineTest {

    public static void main(String args[]) {

      Coordinate a = new Coordinate(7,7);
      Coordinate b = new Coordinate(7,7);
      Coordinate c = new Coordinate(7,7);

      Word cat = new Word(c, true, "cat");
      Word foobar = new Word(c, true, "foobar");
      Word power = new Word(c, true, "power");

      GameBoard gb = new GameBoard();

      System.out.println(GameEngine.computeWordScore(cat, gb));
      System.out.println(GameEngine.computeWordScore(foobar, gb));
      System.out.println(GameEngine.computeWordScore(power, gb));
    }
}
