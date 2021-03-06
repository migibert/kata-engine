package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.EngineEvent;

import java.util.Objects;

public class ChallengerRegisteredEvent extends EngineEvent {
    private Challenger challenger;

    public ChallengerRegisteredEvent(Challenger challenger) {
        this.challenger = challenger;
    }

    public Challenger getChallenger() {
        return new Challenger(challenger.getName(), challenger.getBaseUrl());
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challenger);
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
        final ChallengerRegisteredEvent other = (ChallengerRegisteredEvent) obj;
        return Objects.equals(this.challenger, other.challenger);
    }
}
