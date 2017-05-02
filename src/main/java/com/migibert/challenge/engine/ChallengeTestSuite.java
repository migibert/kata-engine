package com.migibert.challenge.engine;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ChallengeTestSuite {
    private ImmutableList<ChallengeTest> tests;

    public ChallengeTestSuite(ChallengeTest... tests) {
        this.tests = ImmutableList.copyOf(tests);
    }

    public List<ChallengeTest> getTests() {
        return tests;
    }
}
