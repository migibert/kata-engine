package com.migibert.challenge.engine;

import java.util.Objects;

public class Score {
    private Challenge challenge;
    private Challenger challenger;
    private int score;

    public Score(int score, Challenge challenge, Challenger challenger) {
        this.score = score;
        this.challenge = challenge;
        this.challenger = challenger;
    }

    public int getScore() {
        return score;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    @Override
    public int hashCode() {
        return Objects.hash(challenge, challenger, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        return Objects.equals(this.challenge, other.challenge)
                && Objects.equals(this.challenger, other.challenger)
                && Objects.equals(this.score, other.score);
    }
}
