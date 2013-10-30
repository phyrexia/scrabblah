package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.player.TileRack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:40 PM
 */
public class GameEngine { //rules, etc
    public static Dictionary dictionary;

    public static void loadDictionary(File dictionaryFile) throws FileNotFoundException {
        dictionary = new Dictionary(dictionaryFile);
    }

    public static boolean isLegalState(GameState gameState) {
        if (dictionary == null) {
            gameState.setErrorMessage("Dictionary not loaded.");
            return false;
        }

        if (gameState.getPlayerList() == null) {
            gameState.setErrorMessage("There are no players.");
            return false;
        }

        //are all cells locked?

        //first turn must include center cell
        if (gameState.getTurn() == 1 && gameState.getGameBoard().getCellAt(new Coordinate(7, 7)).isEmpty()) {
            gameState.setErrorMessage("On the first turn, the center cell must be occupied.");
            return false;
        }

        //if first word played is a one-letter word
        if (gameState.getGameBoard().getNumOccupiedCells() == 1) {
            gameState.setErrorMessage("Single-letter words are not allowed.");
            return false;
        }

        //are all letters contiguous?
        if (!areLettersContiguous(gameState)) {
            gameState.setErrorMessage("Invalid tile placement.");
            return false;
        }

        //get all words on the board.
        ArrayList<Word> words = getWordsOnBoard(gameState.getGameBoard());

        //todo: remove this GameEngine debug code
        System.out.println("words size: " + words.size());
        for (Word word : words) {
            System.out.println("words from board: " + word);
        }

        //ensure all the words on the board are words in the dictionary
        int n = indexOfBadWord(words);
        if (n != -1) {
            String sDisplay = words.get(n).toString().substring(0, 1).toUpperCase() + words.get(n).toString().substring(1);
            gameState.setErrorMessage(sDisplay + " is not a valid word.");
            return false;
        }

        gameState.getGameBoard().setWordList(words);

        //if all of these tests have passed, then we are golden.
        return true;
    }

    private static boolean areLettersContiguous(GameState gameState) {
        GameBoard gameBoard = gameState.getGameBoard();
        ArrayList<BoardCell> neighbors;
        BoardCell boardCell;
        Coordinate coord;
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                coord = new Coordinate(x, y);
                boardCell = gameBoard.getCellAt(coord);
                if (!boardCell.isEmpty()) {
                    neighbors = gameBoard.getCellNeighbors(coord);
                    //all tiles must have at least one tile neighbor
                    if ((neighbors.get(0) == null || neighbors.get(0).isEmpty()) &&
                            (neighbors.get(1) == null || neighbors.get(1).isEmpty()) &&
                            (neighbors.get(2) == null || neighbors.get(2).isEmpty()) &&
                            (neighbors.get(3) == null || neighbors.get(3).isEmpty())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static ArrayList<Word> getWordsOnBoard(GameBoard gameBoard) {
        ArrayList<Word> wordsOnBoard = new ArrayList<Word>();
        StringBuilder stringBuilder;
        Coordinate coord;
        Word word;

        //find words
        //could this be expressed more concisely?
        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    coord = a == 0 ? new Coordinate(j, i) : new Coordinate(i, j);
                    BoardCell boardCell = gameBoard.getCellAt(coord);

                    while (!boardCell.isEmpty()) { //catch the first letter
                        Coordinate head = coord;
                        stringBuilder = new StringBuilder();
                        do {
                            stringBuilder.append(boardCell.getTile().getCharacter()); //add a letter
                            coord = a == 0 ? new Coordinate(++j, i) : new Coordinate(i, ++j); //the next tile
                            if (gameBoard.getCellAt(coord).isEmpty()) { //if the next tile is empty we're done
                                if (stringBuilder.length() > 1) { //one letter does not a word make
                                    word = new Word(head, a == 0 ? Word.HORIZONTAL : Word.VERTICAL, stringBuilder.toString());
                                    wordsOnBoard.add(word);
                                }
                            }
                            boardCell = gameBoard.getCellAt(coord);
                        } while (!gameBoard.getCellAt(coord).isEmpty());
                    }
                }
            }
        }
        return wordsOnBoard;
    }

    public static int indexOfBadWord(ArrayList<Word> words) {
        //returns the index of the first Word in the list that
        //is not in the dictionary,
        //or -1 if all words are in the dictionary
        for (Word w : words) {
            if (!dictionary.contains(w.getWord())) {
                return words.indexOf(w);
            }
        }
        return -1;
    }

    public int indexOfBadString(ArrayList<String> strings) {
        for (String s : strings) {
            if (!dictionary.contains(s)) {
                return strings.indexOf(s);
            }
        }
        return -1;
    }

    public static int computeScore(GameBoard oldBoard, GameBoard currentBoard) {
        int score = 0;
        if (oldBoard == null) { //first turn case
            oldBoard = new GameBoard();
            oldBoard.setWordList(new ArrayList<Word>());
        }

        //get words that are on cb but not ob.
        ArrayList<Word> newWords = currentBoard.getWordList();
        newWords.removeAll(oldBoard.getWordList());

        for (Word w : newWords) {
            score += computeWordScore(w, currentBoard);
        }
        return score;
    }

    private static int computeWordScore(Word w, GameBoard gameBoard) {
        int wordMultiplier = 1;
        int wordScore = 0;
        int x = w.getHead().getX();
        int y = w.getHead().getY();

        char[] charArr = w.getWord().toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            int tileMultiplier = 1;
            BoardCell boardCell = gameBoard.getCellAt(new Coordinate(
                    (w.getOrientation() ? x + i : x), (w.getOrientation() ? y : y + i)));
            int multiplier = boardCell.getMultiplier();

            if (boardCell.isWordMultiplier()) {
                wordMultiplier *= multiplier;
            } else {
                tileMultiplier = multiplier;
            }
            wordScore += (Tile.getValue(charArr[i]) * tileMultiplier);
        }
        return wordScore * wordMultiplier;
    }

    public static Word scrabbleCheater(GameBoard gameBoard, TileRack tileRack, Double skillLevel) {
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

        //TODO: generate startingZone
        for (int numPlayedTiles = 1; numPlayedTiles < 8; numPlayedTiles++) {
            for (Coordinate coord : getStartingCoordinates(gameBoard, numPlayedTiles)) {

            }
        }


        return null;
    }

    private static ArrayList<String> generateAnagramTree(String string) {
        AnagramTree someTree = new AnagramTree(string);
        return someTree.getAnagrams();
    }

    private static HashSet<Coordinate> getStartingCoordinates(GameBoard gameBoard, int numTilesPlayed) {
        HashSet<Coordinate> startingCoordinates = new HashSet<Coordinate>();
        for (int i = 0; i < 15; i++) {
            for (int x = 0; x < 15; x++) {

            }
        }
        return null;
    }
}