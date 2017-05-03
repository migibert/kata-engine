package com.migibert.challenge.engine;

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
}
