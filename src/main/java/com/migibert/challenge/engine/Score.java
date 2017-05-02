package com.migibert.challenge.engine;

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
}
