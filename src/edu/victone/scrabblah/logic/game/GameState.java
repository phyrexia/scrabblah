package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.TileBag;
import edu.victone.scrabblah.logic.player.Player;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:00 PM
 */

public class GameState {
    private GameBoard gameBoard;
    private PlayerList playerList;
    private TileBag tileBag;

    private boolean initialized = false;
    private int turnCounter = 0;
    private Player winner;

    public GameState() {
        GameEngine.initialize();
        gameBoard = new GameBoard();
        tileBag = new TileBag();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setNumberPlayers(int numPlayers) {
        playerList = new PlayerList(numPlayers);
    }

    public boolean addPlayer(Player p) {
        return playerList.add(p);
    }

    public int getNumberPlayers() {
        return playerList.size();
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public boolean startGame() {
        if (getPlayerList() == null) {
            return false;
        }

        getPlayerList().setPointer(new Random().nextInt(getNumberPlayers()));

        for (Player p : getPlayerList()) {
            for (int i = 0; i < 7; i++) {
                p.addTile(getTile());
            }
        }
        return true;
    }

    private boolean placeTile(Tile t, Coordinate coord) {
        if (!gameBoard.getCell(coord).isEmpty()) {
            return false;
        }
        return gameBoard.getCell(coord).setTile(t);
    }

    private Tile removeTile(Coordinate coord) {
        return null;
    }

    private void endTurn() {
        turnCounter++;
        playerList.incrementPointer();
    }

    private void lockOccupiedCells() {
        //todo: implement me
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                gameBoard.getCell(new Coordinate(i, j)).lock();
            }
        }
    }

    public boolean isGameOver() {
        //TODO: implement me properly
        return turnCounter > 10;
    }

    public Player getCurrentPlayer() {
        return playerList.getCurrentPlayer();
    }

    public Player getWinner() {
        return winner;
    }

    public Tile getTile() {
        return tileBag.getTile();
    }

    @Override
    public String toString() {
        //  TODO: implement me
        return "A gamestate draws near.  Command?";
    }

}