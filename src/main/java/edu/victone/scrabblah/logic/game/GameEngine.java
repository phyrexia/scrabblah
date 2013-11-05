package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:40 PM
 */
public class GameEngine { //rules, etc
    public static Dictionary dictionary;// = new Dictionary();

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
        ArrayList<Word> words = gameState.getGameBoard().getWordList();

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

    public static int indexOfBadString(ArrayList<String> strings) {
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
}