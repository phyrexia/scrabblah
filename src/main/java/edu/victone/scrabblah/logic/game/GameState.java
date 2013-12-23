package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.TileBag;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.logic.player.PlayerList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:00 PM
 */

public class GameState {
    private PlayerList playerList;
    private TileBag tileBag;
    private Player winner = null;

    private int turnCounter = 1;
    private String errorMessage;
    private boolean active;
    private Stack<GameBoard> gameBoards;

    public GameState() {
        try {
            GameEngine.loadDictionary(new File("sowpods.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Fatal Error: Dictionary File Not Found.");
            System.exit(1);
        }
        gameBoards = new Stack<>();
        gameBoards.push(new GameBoard());
        tileBag = new TileBag();
        playerList = new PlayerList();

    }

    public GameBoard getGameBoard() {
        return gameBoards.peek();
    }

    public void pushGameBoard(GameBoard newGameBoard) {
        gameBoards.push(newGameBoard);
    }

    public int getNumberRemainingTiles() {
        return tileBag.size();
    }

    public TileBag getTileBag() {
        return tileBag;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player p) {
        playerList.addPlayer(p);
    }

    public int getNumberPlayers() {
        return playerList.size();
    }

    public boolean startGame() {
        if (active) {
            //kaboom
        } else {
            active = true;
        }


        if (getPlayerList() == null) {
            return false;
        }

        getPlayerList().setIndex(new Random().nextInt(getNumberPlayers()));

        for (Player p : getPlayerList()) {
            for (int i = 0; i < 7; i++) {
                p.getTileRack().addTile(tileBag.removeTile());
            }
        }
        return true;
    }

    public void pass() {
        endTurn();
    }

    public void resign() {
        getCurrentPlayer().resign();
    }

    public void swapTiles(ArrayList<Tile> tilesToSwap) {
        getCurrentPlayer().getTileRack().addTiles(tileBag.swapTiles(tilesToSwap));
    }

    public void endTurn() {
        if (GameEngine.isLegalState(this)) {
            Player p = getCurrentPlayer();
            p.addScore(GameEngine.computeScore(gameBoards.elementAt(gameBoards.size() - 2), gameBoards.peek()));

            while (p.getTileRack().size() < 7) {
                p.getTileRack().addTile(tileBag.removeTile());
            }

            playerList.incrementIndex();
            turnCounter++;
        }
    }

    public Player getCurrentPlayer() {
        return playerList.getCurrentPlayer();
    }

    public boolean isActive() {
        return active;
    }

    public boolean isGameOver() {
        if (tileBag.isEmpty()) {
            for (Player p : playerList) {
                if (p.getTileRack().size() == 0) {
                    setWinner(p);
                    return true;
                }
            }
        } else {
            //if all but one player has resigned, the player that has not resigned is the winner.
            ArrayList<Player> activePlayers = playerList.getActivePlayers();
            if (playerList.getActivePlayers().size() == 1) {
                setWinner(activePlayers.get(0));
                return true;
            }
        }
        return false;
    }

    private void setWinner(Player p) {
        winner = p;
    }

    public Player getWinner() {
        return winner;
    }

    public int getTurn() {
        return turnCounter;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        String r = errorMessage;
        errorMessage = null;
        return r;
    }

    public boolean errorPresent() {
        return errorMessage != null;
    }

    @Override
    public String toString() {
        return "A gamestate draws near.  Command?";
    }
}