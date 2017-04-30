package com.migibert.challenge.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface Challenge extends Activable {
    String getId();
    String getTitle();
    String getStatement();
    ChallengeContract getContract();
    int getSuccessScore();
    int getPartialSuccessScore();
    int getFailureScore();
    void setSuccessScore(int score);
    void setPartialSuccessScore(int score);
    void setFailureScore(int score);

    @JsonIgnore
    List<ChallengeTest> getTests();
}
