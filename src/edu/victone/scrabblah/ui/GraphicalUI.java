package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.logic.common.Word;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 7:46 PM
 */
public class GraphicalUI extends UserInterface {

    @Override
    protected int queryNumberPlayers() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Player queryPlayerData(int rank) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected boolean addPlayerToGame(Player player) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected boolean startGame() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void turnLoop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void playTurn(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void pass(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void swap(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected boolean play(Player player, Word word, Coordinate coord, boolean orientation) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void resign(Player p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void displayGame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
