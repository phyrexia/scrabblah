package edu.victone.scrabblah.logic.player;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:23 PM
 */

public class AIPlayer extends Player {
    public AIPlayer(String name, int rank) {
        super(name, rank);
        isHuman = false;
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
