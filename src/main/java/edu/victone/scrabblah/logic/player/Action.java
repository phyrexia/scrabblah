package edu.victone.scrabblah.logic.player;

import edu.victone.scrabblah.logic.common.*;

/**
 * Created by vwilson on 12/31/13!
 */
public class Action {

  public static final Action PASS = new Action("pass");

  private String type;
  private Word word;

  public Action(String type, Word word) {
    this(type);
    this.word = word;
  }

  public Action(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Word getWord() {
    return word;
  }

  public void setWord(Word word) {
    this.word = word;
  }

}
