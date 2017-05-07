package com.migibert.challenge.event.engine;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.GameEvent;

import java.util.Objects;

public class ChallengerTestSuiteStartedEvent extends GameEvent {
    private Challenger challenger;
    private Challenge challenge;

    public ChallengerTestSuiteStartedEvent(String gameId, Challenge challenge, Challenger challenger) {
        super(gameId);
        this.challenge = challenge;
        this.challenger = challenger;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challenger, challenge);
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
        final ChallengerTestSuiteStartedEvent other = (ChallengerTestSuiteStartedEvent) obj;
        return Objects.equals(this.challenger, other.challenger)
                && Objects.equals(this.challenge, other.challenge);
    }
}
