package com.migibert.challenge.event.registry;

import com.migibert.challenge.event.EngineEvent;

import java.util.Objects;

public class ChallengerUnregisteredEvent extends EngineEvent {
    private String challengerName;

    public ChallengerUnregisteredEvent(String challengerName) {
        this.challengerName = challengerName;
    }

    public String getChallengerName() {
        return challengerName;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challengerName);
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
        return Objects.equals(this.challengerName, other.challengerName);
    }
}
