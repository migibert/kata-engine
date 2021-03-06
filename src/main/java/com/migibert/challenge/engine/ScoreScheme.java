package com.migibert.challenge.engine;

public class ScoreScheme {
    private int successScore;
    private int partialSuccessScore;
    private int failureScore;

    public ScoreScheme() {
    }

    public ScoreScheme(int successScore, int partialSuccessScore, int failureScore) {
        this.successScore = successScore;
        this.partialSuccessScore = partialSuccessScore;
        this.failureScore = failureScore;
    }

    public int getFailureScore() {
        return failureScore;
    }

    public void setSuccessScore(int successScore) {
        this.successScore = successScore;
    }

    public int getPartialSuccessScore() {
        return partialSuccessScore;
    }

    public void setPartialSuccessScore(int partialSuccessScore) {
        this.partialSuccessScore = partialSuccessScore;
    }

    public int getSuccessScore() {
        return successScore;
    }

    public void setFailureScore(int failureScore) {
        this.failureScore = failureScore;
    }

    public static ScoreScheme defaultScheme() {
        return new ScoreScheme(2, 1, 0);
    }
}