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
        System.out.println("GameBoard initialized.");
    }

    @Override
    public String toString() {
        String header = "  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15\n";
        String row = " |--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|\n";
        StringBuilder sb = new StringBuilder(header);
        sb.append(row);

        for (int i = 0; i < 15; i++) {
            sb.append((char) (i + 65));
            sb.append("|");
            //sb.append( i < 10 ? (" " + i + "|") : ("" + i + "|") );
            for (int j = 0; j < 15; j++) {
                //TODO: this
                BoardCell bc = board[i][j];
                if (bc.isEmpty()) {
                    switch (bc.getMultiplier()) {
                        case 1:
                            sb.append("  |");
                            break;
                        case 2:
                            sb.append(bc.affectsWord() ? "DW|" : "DL|");
                            break;
                        case 3:
                            sb.append(bc.affectsWord() ? "TW|" : "TL|");
                            break;
                    }
                } else {
                    sb.append(bc.getTile().toString());
                }
            }
            sb.append("\n");
            sb.append(row);
        }
        return sb.toString();
    }
}