package edu.victone.scrabblah.logic.player;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.game.AnagramTree;
import edu.victone.scrabblah.logic.game.GameBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:23 PM
 */

public class AIPlayer extends Player {
    private static String[] playerNames = {"Charles B.", "Bill G.", "Steve J.", "Steve W.", "Alan T.", "John V.N.", "Bob H.", "Ken S.", "John J."};
    private static Random random = new Random();
    private double skillLevel;

    public AIPlayer(String name) {
        this(name, 1.0); //creates godlike scrabble players
    }

    public AIPlayer(String name, double skillLevel) {
        super(name);
        this.skillLevel = skillLevel;

    }

    public Word takeTurn() {
        return null;
    }

    private static Word scrabbleCheater(GameBoard gameBoard, TileRack tileRack, Double skillLevel) {
        //threading???

        if (skillLevel == null) {
            skillLevel = 1.0;
        }

        if (skillLevel.compareTo(0.0) < 0 || skillLevel.compareTo(1.0) > 0) {
            throw new IllegalArgumentException("Skill Level must be between 0 and 1.");
        }

        ArrayList<Word> possiblePlays = new ArrayList<Word>();

        ArrayList<String> anagrams = generateAnagramTree(null); //tiles

        //todo: generate anagrams

        ArrayList<Coordinate> startingZone = new ArrayList<>();

        //TODO: generate startingZone
        for (int numPlayedTiles = 1; numPlayedTiles < 8; numPlayedTiles++) {
            for (Coordinate coord : getStartingCoordinates(gameBoard, numPlayedTiles)) {

            }
        }

        return null; //return the word to play
    }

    private static ArrayList<String> generateAnagramTree(String string) {
        AnagramTree someTree = new AnagramTree(string);
        return someTree.getAnagrams();
    }

    private static HashSet<Coordinate> getStartingCoordinates(GameBoard gameBoard, int numTilesPlayed) {
        //todo
        HashSet<Coordinate> startingCoordinates = new HashSet<Coordinate>();
        for (int i = 0; i < 15; i++) {
            for (int x = 0; x < 15; x++) {
            //TODO: this
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " (AI) - Score: " + score;
    }

    public static String getRandomName() {
        return playerNames[random.nextInt(playerNames.length)];
    }
}
