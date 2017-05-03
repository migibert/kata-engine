package com.migibert.challenge.engine;

public interface ChallengeTest {
    String getName();
    ChallengeTestResult evaluate(String url);
}
