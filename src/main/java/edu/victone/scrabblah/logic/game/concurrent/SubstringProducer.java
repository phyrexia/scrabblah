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
public class SubstringProducer implements Runnable {
    private LinkedBlockingQueue<String> substringWorkPool;

    private Scanner scanner;
    private HashSet<String> dictionary;

    public SubstringProducer(File dictionaryFile,
                             HashSet<String> dictionary,
                             LinkedBlockingQueue<String> substringWorkPool) throws FileNotFoundException {
        scanner = new Scanner(dictionaryFile);

        this.dictionary = dictionary;
        this.substringWorkPool = substringWorkPool;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (scanner.hasNext()) {
            String input = scanner.next().toUpperCase();
            dictionary.add(input);
            substringWorkPool.offer(input);
        }
        substringWorkPool.offer("@"); //eof marker
        System.out.println("producer finished in " + (System.currentTimeMillis() - start) + "ms.");
    }
}
