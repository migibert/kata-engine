package com.migibert.challenge.engine;

public class Score {
    private Challenge challenge;
    private Challenger challenger;

    private int score;
    private long timestamp;

    public Score(int score, Challenge challenge, Challenger challenger) {
        this.timestamp = System.currentTimeMillis();
        this.score = score;
        this.challenge = challenge;
        this.challenger = challenger;
    }

    public int getScore() {
        return score;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Challenger getChallenger() {
        return challenger;
    }
}
