package com.migibert.challenge.event.engine;

import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.GameEvent;

import java.util.Objects;

public class ChallengerTestStartedEvent extends GameEvent {

    private ChallengeTest test;
    private Challenger challenger;

    public ChallengerTestStartedEvent(String gameId, ChallengeTest test, Challenger challenger) {
        super(gameId);
        this.test = test;
        this.challenger = challenger;
    }

    public ChallengeTest getTest() {
        return test;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(test, challenger);
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
        final ChallengerTestStartedEvent other = (ChallengerTestStartedEvent) obj;
        return Objects.equals(this.test, other.test)
                && Objects.equals(this.challenger, other.challenger);
    }
}
