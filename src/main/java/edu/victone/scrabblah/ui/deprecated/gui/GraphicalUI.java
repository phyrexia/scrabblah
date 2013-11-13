package edu.victone.scrabblah.ui.deprecated.gui;

import edu.victone.scrabblah.logic.common.Move;
import edu.victone.scrabblah.logic.player.Player;
import edu.victone.scrabblah.ui.deprecated.UserInterface;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 7:46 PM
 */

@Deprecated
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
