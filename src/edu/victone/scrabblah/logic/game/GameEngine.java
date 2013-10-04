package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.player.TileRack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:40 PM
 */
public class GameEngine {
    public static void loadDictionary() {
        try {
            File f = new File("sowpods.txt");
            Dictionary.loadDictionary(f);
        } catch (FileNotFoundException e) {
            System.err.println("Fatal Error: Dictionary File Not Found.");
            System.exit(1);
        }
    }

    public static boolean isLegalState(GameState state) throws Exception {
        if (!Dictionary.isLoaded()) {
            throw new Exception("GameEngine not initialized.");
        }

        if (state.getPlayerList() == null) {
            state.setErrorMessage("There are no players.");
            return false;
        }

        //first turn must include center cell
        GameBoard board = state.getGameBoard();
        if (state.getTurn() == 1 && board.getCell(new Coordinate(7, 7)).isEmpty()) {
            state.setErrorMessage("On the first turn, the center cell must be occupied.");
            return false;
        }

        //if first word played is a one-letter word
        if (board.getNumOccupiedCells() == 1 && board.getCell(new Coordinate(7, 7)).isEmpty()) {
            state.setErrorMessage("On the first turn, the center cell must be occupied.");
            return false;
        }

        //are all letters contiguous?
        ArrayList<BoardCell> neighbors;
        BoardCell boardCell;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Coordinate coord = new Coordinate(i, j);
                boardCell = board.getCell(coord);
                if (boardCell.isEmpty()) {
                    continue;
                }
                neighbors = board.getCellNeighbors(coord);


                if (neighbors.get(0) == null &&
                        neighbors.get(1) == null &&
                        neighbors.get(2) == null &&
                        neighbors.get(3) == null) {
                    state.setErrorMessage("Invalid tile placement.");
                    return false;
                }
            }
        }

        //get all words on the board.
        ArrayList<String> words = new ArrayList<String>();
        StringBuilder word = null;
        Coordinate c;

        //horizontal words
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                c = new Coordinate(j, i);
                BoardCell bc = board.getCell(c);
                while (!bc.isEmpty()) { //catch the first letter
                    word = new StringBuilder();
                    do {
                        word.append(bc.getTile().getCharacter()); //add a letter
                        try {
                            c = new Coordinate(++j, i); //the next tile
                        } catch (NullPointerException e) {
                            System.out.println("caught npe.");
                        } finally {
                            System.out.println(c);
                        }
                        if (board.getCell(c).isEmpty()) { //if the next tile is empty we're done
                            if (word.length() > 1) //one letter does not a word make
                                words.add(word.toString());
                        }

                        bc = board.getCell(c);
                    } while (!board.getCell(c).isEmpty());
                }
            }
        }

        //vertical words
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                c = new Coordinate(j, i);
                BoardCell bc = board.getCell(c);
                while (!bc.isEmpty()) { //catch the first letter
                    word = new StringBuilder();
                    do {
                        word.append(bc.getTile().getCharacter()); //add a letter
                        try {
                            c = new Coordinate(i, ++j); //the next tile
                        } catch (NullPointerException e) {
                            System.out.println("caught npe.");
                        } finally {
                            System.out.println(c);
                        }
                        if (board.getCell(c).isEmpty()) { //if the next tile is empty we're done
                            if (word.length() > 1)
                                words.add(word.toString());
                        }

                        bc = board.getCell(c);
                    } while (!board.getCell(c).isEmpty());

                }
            }
        }

        //DEBUG
        //todo: remove this GameEngine debug code
        System.out.println("words size: " + words.size());
        for (String s : words) {
            System.out.println(s);
        }
        //end debug

        for (String s : words) {
            if (!Dictionary.contains(s)) {
                String sDisplay = s.substring(0, 1).toUpperCase() + s.substring(1);
                state.setErrorMessage(sDisplay + " is not a valid word.");
                return false;
            }
        }

        //if all of these tests have passed, then we are golden.
        return true;
    }

    public static int computeScore(GameBoard oldBoard, GameBoard currentBoard) {
        return 42;
    }

    public static Word scrabbleCheater(GameBoard gameBoard, TileRack tileRack) {
        //this may be one of the most difficult methods of the game
        return null;
    }
}
