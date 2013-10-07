package edu.victone.scrabblah.logic.game;

import edu.victone.scrabblah.logic.common.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 4:03 PM
 */

public class Dictionary implements Iterable<String> {
    private Set<String> dictionary;

    public Dictionary(File dictionaryFile) throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        final Scanner scanner = new Scanner(dictionaryFile);
        dictionary = new HashSet<String>();
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (scanner.hasNext()) {
                    dictionary.add(scanner.next().toUpperCase());
                }
                System.out.println("\n(Processed dictionary file in " + (System.currentTimeMillis() - start) + "ms.)");
            }
        });
        t.start();
    }

    public boolean contains(Word w) {
        return contains(w.getWord());
    }

    public boolean contains(String s) {
        if (!dictionary.contains(s.toUpperCase())) {
            return false;
        }
        return true;
    }

    public int indexOfBadWord(ArrayList<Word> words) {
        //returns the index of the first Word in the list that
        //is not in the dictionary,
        //or -1 if all words are in the dictionary
        for (Word w : words) {
            if (!contains(w.getWord())) {
                return words.indexOf(w);
            }
        }
        return -1;
    }

    public int indexOfBadString(ArrayList<String> strings) {
        //returns the index of the first string in the list that
        //is not in the dictionary,
        //or -1 if all string are in the dictionary
        for (String s : strings) {
            if (!contains(s)) {
                return strings.indexOf(s);
            }
        }
        return -1;
    }

    @Override
    public Iterator<String> iterator() {
        return dictionary.iterator();
    }
}
