package com.migibert.challenge.event.game;

import com.google.common.collect.ImmutableList;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.GameEvent;

import java.util.List;
import java.util.Objects;

public class ChallengeStartedEvent extends GameEvent {
    private Challenge challenge;
    private ImmutableList<Challenger> challengers;

    public ChallengeStartedEvent(String gameId, Challenge challenge, List<Challenger> challengers) {
        super(gameId);
        this.challenge = challenge;
        this.challengers = ImmutableList.copyOf(challengers);
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public ImmutableList<Challenger> getChallengers() {
        return challengers;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challenge, challengers);
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
        final ChallengeStartedEvent other = (ChallengeStartedEvent) obj;
        return Objects.equals(this.challenge, other.challenge)
                && Objects.equals(this.challengers, other.challengers);
    }
}
