package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.EngineEvent;

import java.util.Objects;

public class ChallengerUnregisteredEvent extends EngineEvent {
    private Challenger challenger;

    public ChallengerUnregisteredEvent(Challenger challenger) {
        this.challenger = new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
    }

    public Challenger getChallenger() {
        return new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
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
        final ChallengerUnregisteredEvent other = (ChallengerUnregisteredEvent) obj;
        return Objects.equals(this.challenger, other.challenger);
    }
}
