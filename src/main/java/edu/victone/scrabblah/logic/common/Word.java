package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: vwilson
 * Date: 9/11/13
 * Time: 5:06 PM
 */

public class Word {
  private String playedWord;
  private Coordinate head;
  private int score;
  private boolean orientation;

  public Word(Coordinate head, boolean orientation, String string) {
    this.head = head;
    this.orientation = orientation;
    this.playedWord = string;

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

  public String getPlayedWord() {
    return playedWord;
  }

  @Override
  public String toString() {
    return playedWord + (orientation ? " horizontal at " : " vertical at ") + head;
  }

  @Override
  public boolean equals(Object o) {
    if (getClass() != o.getClass()) {
      return false;
    }

    final Word w = (Word) o;
    return (playedWord.equals(w.playedWord) && (orientation && w.orientation) && head.equals(w.head));
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(playedWord, head, orientation);
  }
}