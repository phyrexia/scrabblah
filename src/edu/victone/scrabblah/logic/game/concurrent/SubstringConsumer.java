package edu.victone.scrabblah.logic.game.concurrent;

import edu.victone.scrabblah.logic.game.SubstringDB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/23/13
 * Time: 1:51 AM
 */
public class SubstringConsumer implements Runnable {
    LinkedBlockingQueue<String> substringWorkPool;
    SubstringDB substrings;

    public SubstringConsumer(LinkedBlockingQueue<String> substringWorkPool, SubstringDB substrings) {
        this.substringWorkPool = substringWorkPool;
        this.substrings = substrings;
    }

    @Override
    public void run() {
        String input;
        while (true) { //hax!
            try {
                input = substringWorkPool.poll(50, TimeUnit.MILLISECONDS);
                if (input == null) {
                    System.out.println("Breaking after adding " + substrings.size() + " words to sst.");
                    break;
                }
                substrings.add(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
