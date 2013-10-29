package edu.victone.scrabblah.logic.game.concurrent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/23/13
 * Time: 12:01 AM
 */
public class AnagramConsumer implements Runnable {
    private LinkedBlockingQueue<String> anagramClassWorkPool;
    private HashMap<String, HashSet<String>> anagrams;

    public AnagramConsumer(LinkedBlockingQueue<String> anagramClassWorkPool,
                           HashMap<String, HashSet<String>> anagrams) {
        this.anagramClassWorkPool = anagramClassWorkPool;
        this.anagrams = anagrams;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        String input = "";
        while (true) {
            try {
                input = anagramClassWorkPool.take();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (input.equals("@")) {
                System.out.println("Breaking after adding " + anagrams.size() + " anagrams.");
                break;
            }
            char[] charArr = input.toCharArray();
            Arrays.sort(charArr);
            String sortedString = new String(charArr);
            if (!anagrams.containsKey(sortedString)) {
                anagrams.put(sortedString, new HashSet<String>());
            }
            anagrams.get(sortedString).add(input);
        }
        System.out.println("Anagrams processed in " + (System.currentTimeMillis() - start) + "ms.");
    }
}
