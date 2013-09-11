package edu.victone.scrabblah;

import edu.victone.scrabblah.ui.GraphicalUIAdapter;import edu.victone.scrabblah.ui.UserInterfaceAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 2:53 PM
 */
public class Driver {
    public static void main(String[] args) {
        // TODO: MAKE IT HAPPEN
        UserInterfaceAdapter uia;

        System.out.println("");
        if (args.length > 1 && args[1].equals("-g")) { //gui mode
            uia = new GraphicalUIAdapter();



        } else { //console mode
            uia = new ConsoleUIAdapter();

        }
    }
}
