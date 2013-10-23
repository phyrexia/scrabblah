package edu.victone.scrabblah.logic.game.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 10/23/13
 * Time: 12:01 AM
 */
public class Producer implements Runnable {
    private LinkedBlockingQueue<String> substringWorkPool;
    private LinkedBlockingQueue<String> anagramClassWorkPool;

    private File dictionaryFile;
    private Scanner scanner;
    private HashSet<String> dictionary;

    public Producer(File dictionaryFile,
                    HashSet<String> dictionary,
                    LinkedBlockingQueue<String> substringWorkPool,
                    LinkedBlockingQueue<String> anagramClassWorkPool) throws FileNotFoundException {
        this.dictionaryFile = dictionaryFile;
        scanner = new Scanner(dictionaryFile);

        this.dictionary = dictionary;
        this.substringWorkPool = substringWorkPool;
        this.anagramClassWorkPool = anagramClassWorkPool;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        System.out.println("start prod: " + start);

        while (scanner.hasNext()) {
            String input = scanner.next().toUpperCase();
            dictionary.add(input);
            substringWorkPool.offer(input);
            anagramClassWorkPool.offer(input);
        }
        System.out.println("producer finished in " + (System.currentTimeMillis() - start) + "ms.");
    }
}
