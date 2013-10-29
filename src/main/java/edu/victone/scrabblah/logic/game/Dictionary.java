package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.game.concurrent.Producer;
import edu.victone.scrabblah.logic.game.concurrent.SubstringConsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:03 PM
 */

public class Dictionary {
    private HashSet<String> dictionary;
    //private HashMap<String, HashSet<String>> anagrams;
    private PatriciaTrie substrings;

    private LinkedBlockingQueue<String> anagramClassWorkPool;
    private LinkedBlockingQueue<String> substringWorkPool;

    private long timeToInit;

    public Dictionary(File dictionaryFile) throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        final Scanner scanner = new Scanner(dictionaryFile);

        dictionary = new HashSet<String>(360000);
        //anagrams = new HashMap<String, HashSet<String>>();
        substrings = new PatriciaTrie();

        //anagramClassWorkPool = new LinkedBlockingQueue<String>(); //for later
        substringWorkPool = new LinkedBlockingQueue<String>(); //for later

        Producer producer = new Producer(dictionaryFile, dictionary, substringWorkPool, anagramClassWorkPool);
        SubstringConsumer substringConsumer = new SubstringConsumer(substringWorkPool, substrings);
        //AnagramConsumer anagramConsumer = new AnagramConsumer(anagramClassWorkPool, anagrams);

        new Thread(producer).start();
        new Thread(substringConsumer).start();
        //new Thread(anagramConsumer).start();
    }

    public boolean contains(String s) {
        return dictionary.contains(s.toUpperCase());
    }
}