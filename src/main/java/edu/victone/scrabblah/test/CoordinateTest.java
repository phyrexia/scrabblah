package edu.victone.scrabblah.test;

import edu.victone.scrabblah.logic.common.*;

/**
 * Created by vwilson on 12/30/13!
 */
public class CoordinateTest {

  public static void main(String... args) {
    try {
      Coordinate a = new Coordinate(0, 0);
      Coordinate b = new Coordinate(0, 14);
      Coordinate c = new Coordinate(14, 0);
      Coordinate d = new Coordinate(14, 14);

      System.out.println("construction test: pass");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test: fail");
    }

    try {
      Coordinate a = new Coordinate(-1, 0);
      System.out.println("construction test a: fail");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test a: pass, " + e);
    }

    try {
      Coordinate b = new Coordinate(15, 0);
      System.out.println("construction test b: fail");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test b: pass, " + e);
    }

    try {
      Coordinate c = new Coordinate(14, -1);
      System.out.println("construction test c: fail");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test c: pass, " + e);
    }

    try {
      Coordinate d = new Coordinate(14, 15);
      System.out.println("construction test d: fail");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test d: pass, " + e);
    }

    try {
      Coordinate e = new Coordinate(15, 15);
      System.out.println("construction test e: fail");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test e: pass, " + e);
    }

    try {
      Coordinate a = new Coordinate('p', 0);
      System.out.println("construction test f: fail");
    } catch (IllegalArgumentException e) {
      System.out.println("construction test f: pass, " + e);
    }

    Coordinate a = new Coordinate(7, 7);
    Coordinate b = new Coordinate(7, 7);

    System.out.print("equality test: ");
    if (a.equals(b)) {
      System.out.println("pass");
    } else {
      System.out.println("fail");
    }

    Coordinate h = new Coordinate(Coordinate.characterToInt('h'), 7);
    System.out.println("toString(): " + h);
  }
}
