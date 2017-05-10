package com.migibert.challenge.engine;

import java.util.Objects;

public class Score {
    private String gameId;
    private String challengeId;
    private String challengerId;
    private int score;

    public Score(String gameId, int score, String challengeId, String challengerId) {
        this.gameId = gameId;
        this.score = score;
        this.challengeId = challengeId;
        this.challengerId = challengerId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getScore() {
        return score;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public String getChallengerId() {
        return challengerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, challengeId, challengerId, score);
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
        return Objects.equals(this.challengeId, other.challengeId)
                && Objects.equals(this.challengerId, other.challengerId)
                && Objects.equals(this.score, other.score)
                && Objects.equals(this.gameId, other.gameId);
    }
}
