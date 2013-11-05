package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.Word;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:00 PM
 */
public class GameBoard {
    static int[][] cellValues =
            {{4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4},
                    {0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},
                    {0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},
                    {1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},
                    {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                    {0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},
                    {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                    {4, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, 1, 0, 0, 4},
                    {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                    {0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},
                    {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                    {1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},
                    {0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},
                    {0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},
                    {4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4}};

    private BoardCell[][] boardCells = new BoardCell[15][15];
    private ArrayList<Word> wordList;

    public GameBoard() {
        initBoard();
    }

    public GameBoard(GameBoard gb) {
        initBoard();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!boardCells[i][j].isEmpty()) {
                    Coordinate coord = new Coordinate(i, j);
                    boardCells[i][j].setTile(new Tile(gb.getCellAt(coord).getTile()));
                    if (gb.getCellAt(coord).isLocked()) {
                        boardCells[i][j].lock();
                    }
                }
            }
        }
    }

    private void initBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                switch (cellValues[i][j]) {
                    case -1: //star cell
                    case 0:  //regular
                        boardCells[i][j] = new BoardCell(1, false);
                        break;
                    case 1:  //dublet
                        boardCells[i][j] = new BoardCell(2, false);
                        break;
                    case 2:  //triplet
                        boardCells[i][j] = new BoardCell(3, false);
                        break;
                    case 3:  //dubword
                        boardCells[i][j] = new BoardCell(2, true);
                        break;
                    case 4:  //tripword
                        boardCells[i][j] = new BoardCell(3, true);
                        break;
                }
            }
        }
    }

    private static ArrayList<Word> getWordsOnBoard(GameBoard gameBoard) {
        ArrayList<Word> wordsOnBoard = new ArrayList<Word>();
        StringBuilder stringBuilder;
        Coordinate coord;
        Word word;

        //find words
        //could this be expressed more concisely?
        //cause it's sort of a bear. but also sort of awesome?
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

    public BoardCell getCellAt(Coordinate coord) {
        return boardCells[coord.getY()][coord.getX()];
    }

    public ArrayList<BoardCell> getCellNeighbors(Coordinate coord) {
        ArrayList<BoardCell> retVal = new ArrayList<BoardCell>(4);

        retVal.add(coord.getY() > 0 ? getCellAt(new Coordinate(coord.getX(), coord.getY() - 1)) : null);
        retVal.add(coord.getY() < 14 ? getCellAt(new Coordinate(coord.getX(), coord.getY() + 1)) : null);
        retVal.add(coord.getX() < 14 ? getCellAt(new Coordinate(coord.getX() + 1, coord.getY())) : null);
        retVal.add(coord.getX() > 0 ? getCellAt(new Coordinate(coord.getX() - 1, coord.getY())) : null);

        return retVal;
    }

    public int getNumOccupiedCells() {
        int ctr = 0;
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                if (!boardCells[i][j].isEmpty())
                    ctr++;
        return ctr;
    }

    public void lockOccupiedCells() {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                getCellAt(new Coordinate(i, j)).lock();
    }

    public ArrayList<Word> getWordList() {
        return wordList == null ? wordList = getWordsOnBoard(this) : wordList;
    }

    @Override
    public String toString() {
        String header = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O\n";
        String row = " |---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|";
        StringBuilder sb = new StringBuilder(header);
        sb.append(row);
        sb.append("\n");
        for (int i = 0; i < 15; i++) {
            sb.append(new Integer(i+1).toString());
            sb.append("|");
            for (int j = 0; j < 15; j++) {
                BoardCell bc = boardCells[i][j];
                if (bc.isEmpty()) {
                    switch (bc.getMultiplier()) {
                        case 1:
                            sb.append("   |");
                            break;
                        case 2:
                            sb.append(bc.isWordMultiplier() ? "DW |" : "DL |");
                            break;
                        case 3:
                            sb.append(bc.isWordMultiplier() ? "TW |" : "TL |");
                            break;
                    }
                } else {
                    sb.append(bc.getTile().toString());
                    sb.append(bc.getTile().toString().length() == 2 ? " |" : "|");
                }
            }
            sb.append((char) (i + 65));
            sb.append("\n");
            sb.append(row);
            sb.append("\n");
        }
        sb.append(header);
        return sb.toString();
    }
}