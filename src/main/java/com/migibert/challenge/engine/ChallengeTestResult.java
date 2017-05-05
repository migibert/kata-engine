package com.migibert.challenge.engine;

import java.util.Objects;

public class ChallengeTestResult {
    private boolean success;
    private String reason;

    public ChallengeTestResult(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, reason);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChallengeTestResult other = (ChallengeTestResult) obj;
        return Objects.equals(this.success, other.success)
                && Objects.equals(this.reason, other.reason);
    }
}
