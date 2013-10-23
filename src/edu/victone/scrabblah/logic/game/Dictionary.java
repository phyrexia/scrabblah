package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Word;
import edu.victone.scrabblah.logic.game.concurrent.AnagramConsumer;
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

//anagram class creation adds about 500ms to thread execution time
//and fwiw i can't remember why i was so gung-ho about adding it
//as I am writing the complimentary SubstringTree and AnagramTree
//classes
    //it does allow us an extra place to check the anagramtree nodes.

public class Dictionary implements Iterable<String> {
    private HashSet<String> dictionary;
    private HashMap<String, HashSet<String>> anagrams;
    private SubstringDB substrings;

    private LinkedBlockingQueue<String> anagramClassWorkPool;
    private LinkedBlockingQueue<String> substringWorkPool;

    private long timeToInit;

    public Dictionary(File dictionaryFile) throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        final Scanner scanner = new Scanner(dictionaryFile);

        dictionary = new HashSet<String>();
        anagrams = new HashMap<String, HashSet<String>>();
        substrings = new SubstringDB();

        anagramClassWorkPool = new LinkedBlockingQueue<String>(); //for later
        substringWorkPool = new LinkedBlockingQueue<String>(); //for later

        Producer producer = new Producer(dictionaryFile, dictionary, substringWorkPool, anagramClassWorkPool);
        new Thread(producer).start();

        AnagramConsumer anagramConsumer = new AnagramConsumer(anagramClassWorkPool, anagrams);
        new Thread(anagramConsumer).start();
        SubstringConsumer substringConsumer = new SubstringConsumer(substringWorkPool, substrings);
        new Thread(substringConsumer).start();
    }

    public boolean contains(Word w) {
        return contains(w.getWord());
    }

    public boolean contains(String s) {
        return dictionary.contains(s.toUpperCase());
    }

    @Override
    public Iterator<String> iterator() {
        return dictionary.iterator();
    }
}