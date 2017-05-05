package com.migibert.challenge.event.game;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.ChallengeTestSuiteResult;
import com.migibert.challenge.engine.Challenger;

import java.util.Objects;

public class ChallengerTestSuiteEndedEvent extends GameEvent {
    private Challenger challenger;
    private Challenge challenge;
    private ChallengeTestSuiteResult result;

    public ChallengerTestSuiteEndedEvent(String gameId, Challenge challenge, Challenger challenger, ChallengeTestSuiteResult result) {
        super(gameId);
        this.challenge = challenge;
        this.challenger = challenger;
        this.result = result;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    public ChallengeTestSuiteResult getResult() {
        return result;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challenger, challenge, result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final ChallengerTestSuiteEndedEvent other = (ChallengerTestSuiteEndedEvent) obj;
        return Objects.equals(this.challenger, other.challenger)
                && Objects.equals(this.challenge, other.challenge)
                && Objects.equals(this.result, other.result);
    }
}
