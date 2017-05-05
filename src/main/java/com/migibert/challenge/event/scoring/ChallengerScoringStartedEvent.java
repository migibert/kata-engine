package com.migibert.challenge.event.scoring;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.ChallengeTestSuiteResult;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.GameEvent;

import java.util.Objects;

public class ChallengerScoringStartedEvent extends GameEvent {
    private Challenger challenger;
    private Challenge challenge;
    private ChallengeTestSuiteResult result;

    public ChallengerScoringStartedEvent(String gameId, Challenger challenger, Challenge challenge, ChallengeTestSuiteResult result) {
        super(gameId);
        this.challenger = challenger;
        this.challenge = challenge;
        this.result = result;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    public Challenge getChallenge() {
        return challenge;
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
        final ChallengerScoringStartedEvent other = (ChallengerScoringStartedEvent) obj;
        return Objects.equals(this.challenger, other.challenger)
                && Objects.equals(this.challenge, other.challenge)
                && Objects.equals(this.result, other.result);
    }
}
