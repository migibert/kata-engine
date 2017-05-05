package com.migibert.challenge.event;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class EngineEvent {
    private Date date;

    public EngineEvent() {
        this.date = new Date();
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
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
        return Objects.equals(this.date, other.date);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
