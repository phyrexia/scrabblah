package edu.victone.scrabblah.logic.player;

import edu.victone.scrabblah.logic.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:17 PM
 */

public class HumanPlayer extends Player {
    public HumanPlayer(String name, int rank) {
        super(name, rank);
        isHuman = true;
    }

    @Override
    public boolean playWord() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean swap() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean pass() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resign() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
