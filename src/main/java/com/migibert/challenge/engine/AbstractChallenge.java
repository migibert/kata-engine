package com.migibert.challenge.engine;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(successScore, partialSuccessScore, failureScore, active);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AbstractChallenge other = (AbstractChallenge) obj;
        return Objects.equals(this.successScore, other.successScore)
                && Objects.equals(this.partialSuccessScore, other.partialSuccessScore)
                && Objects.equals(this.failureScore, other.failureScore)
                && Objects.equals(this.active, other.active);
    }
}
