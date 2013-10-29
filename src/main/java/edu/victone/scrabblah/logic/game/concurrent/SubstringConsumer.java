package edu.victone.scrabblah.logic.game.concurrent;

import com.google.common.base.Preconditions;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/23/13
 * Time: 1:51 AM
 */
public class SubstringConsumer implements Runnable {
    LinkedBlockingQueue<String> substringWorkPool;
    edu.victone.scrabblah.logic.game.PatriciaTrie substrings;

    public SubstringConsumer(LinkedBlockingQueue<String> substringWorkPool, edu.victone.scrabblah.logic.game.PatriciaTrie substrings) {
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
