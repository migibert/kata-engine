package com.migibert.challenge.engine;

import java.util.Objects;

public abstract class AbstractChallenge implements Challenge {

    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getStatement(), getContract(), getTestSuite(), isActive());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AbstractChallenge other = (AbstractChallenge) obj;
        return Objects.equals(getId(), other.getId())
                && Objects.equals(getTitle(), other.getTitle())
                && Objects.equals(getStatement(), other.getStatement())
                && Objects.equals(getContract(), other.getContract())
                && Objects.equals(getTestSuite(), other.getTestSuite())
                && Objects.equals(isActive(), other.isActive());
    }
}
