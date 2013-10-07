package edu.victone.scrabblah.ui;

import edu.victone.scrabblah.logic.common.Coordinate;
import edu.victone.scrabblah.logic.common.Move;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.logic.common.Word;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 7:46 PM
 */
public class GraphicalUI extends UserInterface {

    @Override
    protected void displayGame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected int queryNumberPlayers() {
        return 0;
    }

    @Override
    protected Player queryPlayerData(int rank) {
        return null;
    }

    @Override
    protected void playTurn(Player p) {
    }

    @Override
    protected Move queryMoveType() {
        return null;
    }

    @Override
    protected void pass(Player p) {
    }

    @Override
    protected void swap(Player p) {
    }

    @Override
    protected void querySwap(Player currentPlayer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void queryPlay(Player currentPlayer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void queryResignation(Player p) {
    }
}
