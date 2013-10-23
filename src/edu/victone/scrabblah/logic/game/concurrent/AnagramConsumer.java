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
    HashMap<String, HashSet<String>> anagrams;

    public AnagramConsumer(LinkedBlockingQueue<String> anagramClassWorkPool,
                           HashMap<String, HashSet<String>> anagrams) {
        this.anagramClassWorkPool = anagramClassWorkPool;
        this.anagrams = anagrams;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        System.out.println("start ac: " + start);
        String input = null;
        while (true) { //hax
            try {
                input = anagramClassWorkPool.poll(50, TimeUnit.MILLISECONDS);
                if (input == null) {
                    System.out.println("Breaking after adding " + anagrams.size() + " anagrams.");
                    break; //which will break out lol
                }
                char[] charArr = input.toCharArray();
                Arrays.sort(charArr);
                String sortedString = new String(charArr);
                if (!anagrams.containsKey(sortedString)) {
                    anagrams.put(sortedString, new HashSet<String>());
                }
                anagrams.get(sortedString).add(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dictionary Anagrams processed in " + (System.currentTimeMillis() - start) + "ms.");
    }
}
