package com.migibert.challenge.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ScoreScheme {
    private int successScore;
    private int partialSuccessScore;
    private int failureScore;

    public ScoreScheme(int successScore, int partialSuccessScore, int failureScore) {
        this.successScore = successScore;
        this.partialSuccessScore = partialSuccessScore;
        this.failureScore = failureScore;
    }

    public int getFailureScore() {
        return failureScore;
    }

    public int getPartialSuccessScore() {
        return partialSuccessScore;
    }

    public int getSuccessScore() {
        return successScore;
    }

    public static ScoreScheme defaultScheme() {
        return new ScoreScheme(2, 1, 0);
    }
}