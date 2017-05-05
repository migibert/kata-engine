package com.migibert.challenge.engine;

import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(results, testSuite);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChallengeTestSuiteResult other = (ChallengeTestSuiteResult) obj;
        return Objects.equals(this.results, other.results)
                && Objects.equals(this.testSuite, other.testSuite);
    }
}
