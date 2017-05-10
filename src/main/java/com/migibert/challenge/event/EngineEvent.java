package com.migibert.challenge.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;
import java.util.Objects;

public class EngineEvent implements Comparable<EngineEvent> {
    private Instant instant;

    public EngineEvent() {
        this.instant = Instant.now();
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(instant);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final EngineEvent other = (EngineEvent) obj;
        return Objects.equals(this.instant, other.instant);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int compareTo(EngineEvent engineEvent) {
        return this.instant.compareTo(engineEvent.instant);
    }
}
