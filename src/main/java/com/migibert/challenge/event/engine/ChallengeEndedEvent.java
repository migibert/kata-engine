package com.migibert.challenge.event.engine;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.event.GameEvent;

import java.util.Objects;

public class ChallengeEndedEvent extends GameEvent {
    private Challenge challenge;

    public ChallengeEndedEvent(String gameId, final Challenge challenge) {
        super(gameId);
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
        final ChallengeEndedEvent other = (ChallengeEndedEvent) obj;
        return Objects.equals(this.challenge, other.challenge);
    }
}

