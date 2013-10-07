package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.player.TileRack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:40 PM
 */
public class GameEngine { //rules, etc
    public static Dictionary dictionary;
    public static HashSet<String> anagramClasses;

    public static void loadDictionary(File dictionaryFile) throws FileNotFoundException {
        dictionary = new Dictionary(dictionaryFile);
        generateAnagramClasses(dictionary);
    }

    private static void generateAnagramClasses(final Dictionary d) {
        anagramClasses = new HashSet<String>();
        final long start = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (String s : d) {
                    char[] ac = s.toCharArray();
                    Arrays.sort(s.toCharArray());
                    anagramClasses.add(new String(ac));
                }
                System.out.println("Generated anagrams in " + (System.currentTimeMillis() - start));
            }
        });
        t.start();
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

        //first turn must include center cell
        GameBoard gameBoard = gameState.getGameBoard();
        if (gameState.getTurn() == 1 && gameBoard.getCell(new Coordinate(7, 7)).isEmpty()) {
            gameState.setErrorMessage("On the first turn, the center cell must be occupied.");
            return false;
        }

        //if first word played is a one-letter word
        if (gameBoard.getNumOccupiedCells() == 1) {
            gameState.setErrorMessage("Single-letter words are not allowed.");
            return false;
        }

        //are all letters contiguous?
        ArrayList<BoardCell> neighbors;
        BoardCell boardCell;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Coordinate coord = new Coordinate(i, j);
                boardCell = gameBoard.getCell(coord);
                if (boardCell.isEmpty()) {
                    continue;
                }
                neighbors = gameBoard.getCellNeighbors(coord);


                if (neighbors.get(0) == null &&
                        neighbors.get(1) == null &&
                        neighbors.get(2) == null &&
                        neighbors.get(3) == null) {
                    gameState.setErrorMessage("Invalid tile placement.");
                    return false;
                }
            }
        }

        //get all words on the board.
        ArrayList<Word> words = getWordsOnBoard(gameBoard);

        //DEBUG
        //todo: remove this GameEngine debug code
        System.out.println("words size: " + words.size());
        for (Word s : words) {
            System.out.println("words from board: " + s);
        }
        //end debug

        //ensure all the words on the board are words in the dictionary
        ArrayList<String> temp = new ArrayList<String>();
        for (Word w : words) {
            temp.add(w.getWord());
        }
        int n = dictionary.indexOfBadString(temp);
        if (n != -1) {
            String sDisplay = words.get(n).toString().substring(0, 1).toUpperCase() + words.get(n).toString().substring(1);
            gameState.setErrorMessage(sDisplay + " is not a valid word.");
            return false;
        }

        gameBoard.setWordList(words);

        //if all of these tests have passed, then we are golden.
        return true;
    }

    private static ArrayList<Word> getWordsOnBoard(GameBoard gameBoard) {
        ArrayList<Word> wordsOnBoard = new ArrayList<Word>();
        StringBuilder stringBuilder;
        Coordinate coord;
        Word word;

        //find words
        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    coord = new Coordinate(j, i);
                    BoardCell boardCell = gameBoard.getCell(coord);

                    while (!boardCell.isEmpty()) { //catch the first letter
                        Coordinate head = coord;
                        stringBuilder = new StringBuilder();
                        do {
                            stringBuilder.append(boardCell.getTile().getCharacter()); //add a letter
                            coord = a == 0 ? new Coordinate(++j, i) : new Coordinate(i, ++j); //the next tile
                            if (gameBoard.getCell(coord).isEmpty()) { //if the next tile is empty we're done
                                if (stringBuilder.length() > 1) { //one letter does not a word make
                                    word = new Word(head, a == 0 ? Word.HORIZONTAL : Word.VERTICAL, stringBuilder.toString());
                                    wordsOnBoard.add(word);
                                }
                            }
                            boardCell = gameBoard.getCell(coord);
                        } while (!gameBoard.getCell(coord).isEmpty());
                    }
                }
            }
        }
        return wordsOnBoard;
    }

    public static int computeScore(GameBoard oldBoard, GameBoard currentBoard) {
        int score = 0;
        if (oldBoard == null) { //first turn case
            oldBoard = new GameBoard();
        }

        //get words that are on cb but not ob.
        ArrayList<Word> oldWords = getWordsOnBoard(oldBoard);
        ArrayList<Word> newWords = getWordsOnBoard(currentBoard);
        newWords.removeAll(oldWords);

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
            BoardCell boardCell = gameBoard.getCell(new Coordinate(
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

        ArrayList<ArrayList<String>> anagrams = new ArrayList<ArrayList<String>>();

        //TODO: generate startingZone
        for (int numPlayedTiles = 1; numPlayedTiles < 8; numPlayedTiles++) {
            for (Coordinate coord : getStartingCoordinates(gameBoard, numPlayedTiles)) {

            }
        }

        //todo: generate anagrams

        return null;
    }

    private static ArrayList<String> getAnagrams(ArrayList<Character> word) {
        //recursion!
        ArrayList<String> anagrams = new ArrayList<String>();
        if (word.size() == 1) {
            //anagrams.add();
        }

        for (int i = 0; i < word.size(); i++) {

        }


        //add all possible anagrams which are in the dictionary
        //(or not heh)

        return anagrams;
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