package com.migibert.challenge.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ScoreScheme {
    @JsonIgnore
    private Challenge challenge;
    private int successScore;
    private int partialSuccessScore;
    private int failureScore;

    public ScoreScheme() {
    }

    public ScoreScheme(Challenge challenge, int successScore, int partialSuccessScore, int failureScore) {
        this.challenge = challenge;
        this.successScore = successScore;
        this.partialSuccessScore = partialSuccessScore;
        this.failureScore = failureScore;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public int getFailureScore() {
        return failureScore;
    }

    public void setFailureScore(int failureScore) {
        this.failureScore = failureScore;
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

    public void setSuccessScore(int successScore) {
        this.successScore = successScore;
    }
}
