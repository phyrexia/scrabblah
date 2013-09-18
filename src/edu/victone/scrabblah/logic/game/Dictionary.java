package edu.victone.scrabblah.logic.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:03 PM
 */
public class Dictionary {
    private static List<String> dictionary;

    public Dictionary(File dictionaryFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(dictionaryFile);

        dictionary = new ArrayList<String>();
        long start = System.currentTimeMillis();
        while (scanner.hasNext()) {
            dictionary.add(scanner.next().toUpperCase());
        }
        System.out.println("Processed dictionary file in " + (System.currentTimeMillis() - start) + " ms.");
    }

    public boolean contains(String s) {
        return dictionary.contains(s.toUpperCase());
    }
}
