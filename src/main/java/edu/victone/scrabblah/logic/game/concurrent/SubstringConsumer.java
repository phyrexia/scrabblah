package edu.victone.scrabblah.logic.game.concurrent;

import edu.victone.scrabblah.logic.game.SubstringDB;

import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.base.*;

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
        this.substringWorkPool = Preconditions.checkNotNull(substringWorkPool);
        this.substrings = Preconditions.checkNotNull(substrings);
    }

    @Override
    public void run() {
        String input = "";
        while (true) {
            try {
                input = substringWorkPool.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (input.equals("@")) {
                System.out.println("Consumer Breaking after adding " + substrings.size() + " words to sst.");
                break;
            }
            substrings.add(input);
        }
    }
}
