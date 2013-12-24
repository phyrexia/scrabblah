package edu.victone.scrabblah.logic.common;

import com.google.common.base.Objects;

import java.util.ArrayList;


/**
 * author: vwilson
 * date: 12/24/13
 */

public class Play {
    private String player;
    private ArrayList<Word> words;
    private Integer turn;

    public Play(int turn, String player, ArrayList<Word> words) {
        this.turn = turn;
        this.player = player;
        this.words = words;
    }

    @Override
    public String toString() {
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
