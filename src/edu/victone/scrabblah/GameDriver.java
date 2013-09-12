package edu.victone.scrabblah;

import edu.victone.scrabblah.ui.GraphicalUIAdapter;
import edu.victone.scrabblah.ui.ConsoleUIAdapter;
import edu.victone.scrabblah.ui.UserInterfaceAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 2:53 PM
 */
public class GameDriver {
    public static void main(String[] args) {
        UserInterfaceAdapter uia;

        if (args.length > 1 && args[1].equals("-g")) { //gui mode
            uia = new GraphicalUIAdapter();


        } else { //console mode
            uia = new ConsoleUIAdapter();


        }
    }
}
