package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:06 PM
 */

public class Word {
  private String word;
  private Coordinate head;
  private int score;
  private boolean orientation;

  public Word(Coordinate head, boolean orientation, String string) {
    this.head = head;
    this.orientation = orientation;
    this.word = string;

  }

  public Word(Coordinate head, boolean orientation) {
    this.head = head;
    this.orientation = orientation;
  }

  public Word(Word w) {
    this.head = w.getHead();
    this.orientation = w.orientation;
    this.word = w.getWord();
  }

  public void setWord(String s) {
    word = s;
  }

  public void setScore(int s) {
    score = s;
  }

  public int getScore() {
    return score;
  }

  public Coordinate getHead() {
    return head;
  }

  public boolean isHorizontal() {
    return orientation;
  }

  public String getWord() {
    return word;
  }

  @Override
  public String toString() {
    return word + (orientation ? " horizontal at " : " vertical at ") + head;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    if (getClass() != o.getClass()) {
      return false;
    }

    final Word w = (Word) o;
    return (word.equals(w.word) && (orientation == w.orientation) && head.equals(w.head));
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(word, head, orientation);
  }
}