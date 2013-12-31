package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;
import edu.victone.scrabblah.logic.player.*;

import java.util.ArrayList;


/**
 * author: vwilson
 * date: 12/24/13
 */

public class Play {
  private String player;
  private ArrayList<Word> words;
  private Integer turn;
  private Action action;

  public Play(int turn, String player, ArrayList<Word> words, Action action) {
    this.turn = turn;
    this.player = player;
    this.words = words;
    this.action = action;
  }

  @Override
  public String toString() {
    switch (action) {
      case PLAY:
        break;
      case SWAP:
        break;
      case PASS:
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

    final Play p = (Play) o;
    return (turn.equals(p.turn) && words.containsAll(p.words) && p.words.containsAll(words) && player.equals(p.player));
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(player, words, turn);
  }
}