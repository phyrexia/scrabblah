package edu.victone.scrabblah.logic;

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
        while (scanner.hasNext()) {
            dictionary.add(scanner.next().toLowerCase());
        }
    }

    public boolean isValidWord(String s) {
        return dictionary.contains(s.toLowerCase()) ? true : false;
    }
}
