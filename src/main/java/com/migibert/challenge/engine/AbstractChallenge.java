package com.migibert.challenge.engine;

import java.util.Objects;

public abstract class AbstractChallenge implements Challenge {

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getStatement(), getContracts(), getTestSuite());
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
                && Objects.equals(getContracts(), other.getContracts())
                && Objects.equals(getTestSuite(), other.getTestSuite());
    }
}
