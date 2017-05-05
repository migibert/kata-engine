package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.event.EngineEvent;

import java.util.Objects;

public class ChallengeDeactivatedEvent extends EngineEvent {
    private Challenge challenge;

    public ChallengeDeactivatedEvent(Challenge challenge) {
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challenge);
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
        final ChallengeDeactivatedEvent other = (ChallengeDeactivatedEvent) obj;
        return Objects.equals(this.challenge, other.challenge);
    }
}
