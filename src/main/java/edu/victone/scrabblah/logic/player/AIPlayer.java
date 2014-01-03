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
  private static String[] playerNames = {"Charles B.", "Bill G.", "Steve J.", "Steve W.", "Alan T.", "John V.N.", "Bob H.", "Ken S.", "John J.",
      "Ada L.", "Grace H.", "Adele G.", "Radia P."};
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
  returns the action we should take after setting appropriate internal variables
   */
  public Action getNextAction(GameState gameState) {
    //to do this we're still going to have to think sort of hard.

    //should we play, swap, or pass?

    if (gameState.getGameBoard().getNumOccupiedCells() == 0) {
      return firstTurn(gameState);
    }

    //play
    //first, we're going to try to brute force words.

    //swap
    //should we think about what to swap or swap randomly?

    //pass
    return Action.PASS;
  }

  private Action firstTurn(GameState gameState) {
    //get the words we can make with the tiles in the rack.
    HashSet<String> possibleWords = new AnagramTree(tileRack.arrayToString()).getAnagrams();
    if (possibleWords.size() == 0) {
      return Action.PASS;
    }

    //sort words by length
    HashMap<Integer, ArrayList<String>> wordsByLength = sortWordsByLength(possibleWords);

    //populate a hashmap, key = wordlength, val = set of halfwords
    HashMap<Integer, ArrayList<Word>> coordsAndOrientations = new HashMap<>(50);
    for (Integer i : wordsByLength.keySet()) {
      coordsAndOrientations.put(i, new ArrayList<Word>());
      coordsAndOrientations.get(i).addAll(getStartingCoordsAndOrientations(gameState, i));
    }

    //join the two maps
    ArrayList<Word> wordsToTry = new ArrayList<>(possibleWords.size() * 4);
    for (Integer i : coordsAndOrientations.keySet()) {
    //for (int i = 2; i <= 7; i++) {
      ArrayList<Word> halfWords = coordsAndOrientations.get(i);

      ArrayList<String> strings = wordsByLength.get(i);

      for (Word w : halfWords) {
        for (String s : strings) {
          Word word = new Word(w);
          word.setWord(s);
          wordsToTry.add(word);
        }
      }
    }

    //sort gameboards by score
    HashMap<Integer, Word> wordsByScore = new HashMap<>();
    for (Word w : wordsToTry) {

      //WHAT THE FUCK
      int score = - GameEngine.computeWordScore(w, gameState.getGameBoard());
      //WHAT THE FUCK

      System.out.println(w + " scores " + score + " points.");
      wordsByScore.put(score, w);
    }

    for (Integer score : wordsByScore.keySet()) {
      //WTF
      System.out.println(score + " " + wordsByScore.get(score));
    }

    //get gameboard with highest score
    int score = 0;
    for (Integer i : wordsByScore.keySet()) {
      if (i.compareTo(score) > 0) {
        wordToPlay = wordsByScore.get(i);
      }
    }
    return Action.PLAY;
  }

  private HashMap<Integer, ArrayList<String>> sortWordsByLength(HashSet<String> possibleWords) {
    HashMap<Integer, ArrayList<String>> wordsByLength = new HashMap<>(9);
    for (String s : possibleWords) {
      Integer len = s.length();
      if (!wordsByLength.containsKey(len)) {
        wordsByLength.put(len, new ArrayList<String>());
      }
      wordsByLength.get(s.length()).add(s);
    }
    return wordsByLength;
  }

  /*
  returns Words that are just the head coordinate and the orientation
  */
  private static ArrayList<Word> getStartingCoordsAndOrientations(GameState gameState, int numTilesPlayed) {
    if (numTilesPlayed > 7 || numTilesPlayed < 0) {
      throw new IllegalArgumentException("Illegal numTilesPlayed");
    }

    ArrayList<Word> unfinishedStartingWords = new ArrayList<>(100);
    if (gameState.getGameBoard().getNumOccupiedCells() == 0) {
      //so just on row 8 and column h
      for (int i = 0; i < numTilesPlayed; i++) {
        int x = 7 - i;
        Coordinate c = new Coordinate(x, 7);
        Coordinate d = new Coordinate(7, x);
        unfinishedStartingWords.add(new Word(c, true));
        unfinishedStartingWords.add(new Word(d, false));
      }
    } else {
      //more work involved...
      for (int i = 0; i < 15; i++) {
        for (int x = 0; x < 15; x++) {
          //TODO: this
        }
      }
    }
    return unfinishedStartingWords;
  }

  public Word getWordToPlay() {
//    Word w = wordToPlay;
//    wordToPlay = null;
//    return w;
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