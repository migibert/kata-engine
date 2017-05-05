package com.migibert.challenge.engine;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

public class ChallengeTestSuite {
    private ImmutableList<ChallengeTest> tests;

    public ChallengeTestSuite(ChallengeTest... tests) {
        this.tests = ImmutableList.copyOf(tests);
    }

    public List<ChallengeTest> getTests() {
        return tests;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tests);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChallengeTestSuite other = (ChallengeTestSuite) obj;
        return Objects.equals(this.tests, other.tests);
    }
}
