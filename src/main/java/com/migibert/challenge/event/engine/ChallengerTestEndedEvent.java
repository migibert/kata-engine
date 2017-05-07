package com.migibert.challenge.event.engine;

import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.ChallengeTestResult;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.GameEvent;

import java.util.Objects;

public class ChallengerTestEndedEvent extends GameEvent {

    private ChallengeTest test;
    private Challenger challenger;
    private ChallengeTestResult result;

    public ChallengerTestEndedEvent(String gameId, ChallengeTest test, Challenger challenger, ChallengeTestResult result) {
        super(gameId);
        this.test = test;
        this.challenger = challenger;
        this.result = result;
    }

    public ChallengeTest getTest() {
        return test;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    public ChallengeTestResult getResult() {
        return result;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(test, challenger, result);
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
        final ChallengerTestEndedEvent other = (ChallengerTestEndedEvent) obj;
        return Objects.equals(this.test, other.test)
                && Objects.equals(this.challenger, other.challenger)
                && Objects.equals(this.result, other.result);
    }
}
