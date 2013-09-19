package edu.victone.scrabblah.logic.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:03 PM
 */

public class Dictionary {
    private static Set<String> dictionary;
    private static boolean isLoaded = false;

    public Dictionary(final File dictionaryFile) throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        final Scanner scanner = new Scanner(dictionaryFile);
        dictionary = new HashSet<String>();
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (scanner.hasNext()) {
                    dictionary.add(scanner.next().toUpperCase());
                }
                System.out.println("(Processed dictionary file in " + (System.currentTimeMillis() - start) + " ms.)");
                isLoaded = true;
            }
        });
        t.start();
    }

    public boolean contains(String s) {
        return dictionary.contains(s.toUpperCase());
    }

    public boolean isLoaded() {
        return isLoaded;
    }
}
