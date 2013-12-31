package edu.victone.scrabblah.logic.player;

import edu.victone.scrabblah.logic.common.*;
import edu.victone.scrabblah.logic.game.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:23 PM
 */

public class AIPlayer extends Player {
  private static String[] playerNames = {"Charles B.", "Bill G.", "Steve J.", "Steve W.", "Alan T.", "John V.N.", "Bob H.", "Ken S.", "John J."};
  private static ArrayList<String> availableNames;
  private static Random random = new Random();

  private Word wordToPlay;
  private ArrayList<Tile> tilesToSwap;
  private double skillLevel;

  public static String getRandomName() {
    if (availableNames == null) {
      initializeAvailableNames();
    }
    return availableNames.remove(random.nextInt(availableNames.size()));
  }

  private static void initializeAvailableNames() {
    availableNames = new ArrayList<>(9);
    Collections.addAll(availableNames, playerNames);
  }

  public AIPlayer() {
    this(getRandomName());
  }

  public AIPlayer(String name) {
    this(name, 1.0);
  }

  public AIPlayer(String name, Double skillLevel) {
    super(name);
    if (skillLevel.compareTo(0.0) < 0 || skillLevel.compareTo(1.0) > 0) {
      throw new IllegalArgumentException("Skill Level must be between 0 and 1.");
    }
    this.skillLevel = skillLevel;

    double d = Math.pow(10, skillLevel);
  }

  @Override
  public boolean isHuman() {
    return false;
  }

  /*
  returns the best word we can think of, or
  a magic word if we should pass, or,
  a magic word if we should swap.
   */
  public Action getNextAction(GameState gameState) {
     //return the action
    //to do this we're still going to have to think sort of hard.

    //do a bunch of stuff


    //randomly swap tiles?


    return Action.PASS;
  }

  public ArrayList<String> generateAnagramTree(String string) {
    return new AnagramTree(string).getAnagrams();
  }

  public static HashSet<Coordinate> getStartingCoordinates(GameBoard gameBoard, int numTilesPlayed) {
    HashSet<Coordinate> startingCoordinates = new HashSet<>(100);
    for (int i = 0; i < 15; i++) {
      for (int x = 0; x < 15; x++) {
        //TODO: this
      }
    }
    return null;
  }

  public Word getWordToPlay() {
    return wordToPlay;
  }

  public ArrayList<Tile> getTilesToSwap() {
    return tilesToSwap;
  }

  @Override
  public String toString() {
    return name + " (AI) - Score: " + score;
  }
}