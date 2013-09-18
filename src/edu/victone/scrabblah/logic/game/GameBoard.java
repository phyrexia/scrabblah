package edu.victone.scrabblah.logic.game;

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


    public static final int MAXPLAYERS = 4;

    private BoardCell[][] board = new BoardCell[15][15];

    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                switch (cellValues[i][j]) {
                    case -1: //star cell
                    case 0:  //regular
                        board[i][j] = new BoardCell(1, false);
                        break;
                    case 1:  //dublet
                        board[i][j] = new BoardCell(2, false);
                        break;
                    case 2:  //triplet
                        board[i][j] = new BoardCell(3, false);
                        break;
                    case 3:  //dubword
                        board[i][j] = new BoardCell(2, true);
                        break;
                    case 4:  //tripword
                        board[i][j] = new BoardCell(3, true);
                        break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                //TODO: this
            }
        }
        return null;
    }
}
