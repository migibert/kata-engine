package com.migibert.challenge.engine;

public abstract class AbstractChallenge implements Challenge {
    private int successScore;
    private int partialSuccessScore;
    private int failureScore;
    private boolean active;

    @Override
    public int getSuccessScore() {
        return successScore;
    }

    @Override
    public int getPartialSuccessScore() {
        return partialSuccessScore;
    }

    @Override
    public int getFailureScore() {
        return failureScore;
    }

    @Override
    public void setSuccessScore(int score) {
        this.successScore = score;
    }

    @Override
    public void setPartialSuccessScore(int score) {
        this.partialSuccessScore = score;
    }

    @Override
    public void setFailureScore(int score) {
        this.failureScore = score;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
