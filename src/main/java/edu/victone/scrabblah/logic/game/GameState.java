package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Tile;
import edu.victone.scrabblah.logic.common.TileBag;
import edu.victone.scrabblah.logic.common.Word;
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

    public void pushGameBoard() {
        gameBoards.push(new GameBoard(gameBoards.peek()));
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

    public boolean addPlayer(Player p) {
        return playerList.addPlayer(p);
    }

    private int getNumberPlayers() {
        return playerList.size();
    }

    public boolean startGame() {
        if (active) {
            setErrorMessage("ERROR: Can't start a game already in progress.");
        } else {
            active = true;
        }


        if (getPlayerList() == null) {
            return false;
        }

        if (getNumberPlayers() <= 1) {


            setErrorMessage("ERROR: Not enough players.  Add " + (2 - getNumberPlayers()) + " to " +
                    (4 - getNumberPlayers()) + " players.");
        }

        getPlayerList().setIndex(new Random().nextInt(getNumberPlayers()));

        for (Player p : getPlayerList()) {
            for (int i = 0; i < 7; i++) {
                p.getTileRack().addTile(tileBag.removeTile());
            }
        }
        return true;
    }

    public boolean play(Word w) {
        //place each tile of w on the board
        //check for validity
        //if valid end turn, return true
        //if not...

        if (GameEngine.dictionary.contains(w.getWord())) {
            ArrayList<Tile> tilesToRemove = new ArrayList<>() ;
            GameBoard newBoard = new GameBoard(getGameBoard());
            //newBoard = gameState.getGameBoard();
            //are all tiles in the player's tile rack (or on the board?)
            Tile t;
            if (w.isHorizontal()) {
                int wx = w.getHead().getX();
                int y = w.getHead().getY();
                for (int x = wx, ptr = 0; x < wx + w.getWord().length(); x++, ptr++ ) {
                    t = new Tile(w.getWord().charAt(ptr));
                    Coordinate c = new Coordinate(x,y);
                    if (getGameBoard().getCellAt(c).isEmpty()) {
                        if (getCurrentPlayer().getTileRack().contains(t)) {
                            tilesToRemove.add(t);
                            newBoard.getCellAt(c).setTile(t);
                        } else {
                            //fail: this tile is not in the rack
                            setErrorMessage("ERROR: Tile " + t + " not in rack");
                            return false;
                        }
                    } else if (getGameBoard().getCellAt(c).getTile().equals(t)) {
                        //same tile, doesn't have to be in rack, so do nothing...

                    } else {
                        //fail, can't play a tile on another tile.
                        setErrorMessage("ERROR: Tile " + t + " doesn't fit");
                        return false;
                    }
                }
            } else {
                int x = w.getHead().getX();
                int wy = w.getHead().getY();
                for (int y = wy, ptr = 0; y < wy + w.getWord().length(); y++, ptr++ ) {
                    t = new Tile(w.getWord().charAt(ptr));
                    Coordinate c = new Coordinate(x,y);
                    if (getGameBoard().getCellAt(c).isEmpty()) {
                        if (getCurrentPlayer().getTileRack().contains(t)) {
                            tilesToRemove.add(t);
                            newBoard.getCellAt(c).setTile(t);
                        } else {
                            //fail: this tile is not in the rack
                            setErrorMessage("ERROR: Tile " + t + " not in rack");
                            return false;

                        }
                    } else if (getGameBoard().getCellAt(c).getTile().equals(t)) {
                        //same tile, doesn't have to be in rack, so do nothing...

                    } else {
                        //fail, can't play a tile on another tile.
                        setErrorMessage("ERROR: Tile " + t + " doesn't fit");
                        return false;
                    }
                }
            }
            //if we made it this far we're good
            if (GameEngine.isLegalGameBoard(newBoard)) {
                pushGameBoard(newBoard);
            } else {
                setErrorMessage("ERROR: Illegal Tile Placement");
                return false;
            }
            for (Tile tile : tilesToRemove) {
                getCurrentPlayer().getTileRack().removeTile(tile);
            }
            return endTurn();
        } else {
            setErrorMessage("ERROR: " + w.getWord() + " is not in the dictionary.");
            return false;
        }
    }



    public void resign() {
        getCurrentPlayer().resign();
    }

    public boolean swapTiles(ArrayList<Tile> tilesToSwap) {
        if (getNumberRemainingTiles() >= tilesToSwap.size()) {


            for (Tile t : tilesToSwap) {
                getCurrentPlayer().getTileRack().removeTile(t);
            }

            getCurrentPlayer().getTileRack().addTiles(tileBag.swapTiles(tilesToSwap));
            playerList.incrementIndex();
            turnCounter++;
            return true;
        } else {
            setErrorMessage("ERROR: Not enough tiles to swap.");
            return false;
        }
    }

    public void pass() {
        playerList.incrementIndex();
        turnCounter++;
    }

    public boolean endTurn() {
        if (GameEngine.isLegalState(this)) {
            Player p = getCurrentPlayer();
            p.addScore(GameEngine.computeScore(gameBoards.elementAt(gameBoards.size() - 2), gameBoards.peek()));

            while (p.getTileRack().size() < 7) {
                p.getTileRack().addTile(tileBag.removeTile());
            }

            playerList.incrementIndex();
            turnCounter++;
            return true;
        } else {
            return false;
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