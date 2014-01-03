package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;
import edu.victone.scrabblah.logic.player.*;

import java.util.ArrayList;


/**
 * author: vwilson
 * date: 12/24/13
 */

public class Turn {
  private String player;
  private ArrayList<Word> words;
  private Integer turn;
  private Action action;

  public Turn(int turn, String player, ArrayList<Word> words, Action action) {
    this.turn = turn;
    this.player = player;
    this.words = words;
    this.action = action;
  }

  @Override
  public String toString() {
    switch (action.getType()) {
      case "play":
        break;
      case "swap":
        break;
      case "pass":
        break;
      default:
        //wtf
    }
    return "Turn " + turn + ": " + player + " played " + words;
  }

  @Override
  public boolean equals(Object o) {
    if (getClass() != o.getClass()) {
      return false;
    }

    final Turn p = (Turn) o;
    return (turn.equals(p.turn) && words.containsAll(p.words) && p.words.containsAll(words) && player.equals(p.player));
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(player, words, turn);
  }
}