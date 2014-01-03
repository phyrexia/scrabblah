package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.common.*;
import edu.victone.scrabblah.logic.game.*;
import edu.victone.scrabblah.logic.player.*;

import java.io.*;

/**
 * Created by vwilson on 12/31/13!
 */
public class AIPlayerTest {
  public static void main(String... args) throws FileNotFoundException {
    GameState gameState = new GameState();
    Dictionary.load(new File("sowpods.txt"));
    TileRack tr = new TileRack();
    AIPlayer ai = new AIPlayer();


    ai.getTileRack().addTile(new Tile('f'));
    ai.getTileRack().addTile(new Tile('o'));
    ai.getTileRack().addTile(new Tile('o'));
    ai.getTileRack().addTile(new Tile('b'));
    ai.getTileRack().addTile(new Tile('a'));
    ai.getTileRack().addTile(new Tile('r'));
    ai.getTileRack().addTile(new Tile('e'));

    ai.getNextAction(gameState);
  }
}
