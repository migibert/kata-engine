package com.migibert.challenge.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ro.fortsoft.pf4j.ExtensionPoint;

import java.util.List;

public interface Challenge extends ExtensionPoint {
    String getId();

    String getTitle();

    String getStatement();

    List<ChallengeContract> getContracts();

    @JsonIgnore
    ChallengeTestSuite getTestSuite();
}
