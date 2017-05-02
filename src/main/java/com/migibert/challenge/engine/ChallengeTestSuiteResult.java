package com.migibert.challenge.engine;

import java.util.List;

public class ChallengeTestSuiteResult {
    private List<ChallengeTestResult> results;
    private ChallengeTestSuite testSuite;

    public ChallengeTestSuiteResult(List<ChallengeTestResult> results, ChallengeTestSuite testSuite) {
        this.results = results;
        this.testSuite = testSuite;
    }

    public List<ChallengeTestResult> getResults() {
        return results;
    }

    public ChallengeTestSuite getTestSuite() {
        return testSuite;
    }

    public int getSuccesses() {
        return (int) results.stream().filter(result -> result.isSuccess()).count();
    }

    public int getFailures() {
        return (int) results.stream().filter(result -> !result.isSuccess()).count();
    }

    public boolean isPassed() {
        return getFailures() == 0;
    }

    public int getTestNumber() {
        return results.size();
    }
}
