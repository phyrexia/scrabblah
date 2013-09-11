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
public class WordList {
    private static List<String> wordList;

    public WordList(File dictionaryFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(dictionaryFile);

        wordList = new ArrayList<String>();
        while (scanner.hasNext()) {
            wordList.add(scanner.next().toLowerCase());
        }
    }

    public boolean isValidWord(String s) {
        if (wordList.contains(s.toLowerCase())) {
            return true;
        }
        return false;
    }
}
