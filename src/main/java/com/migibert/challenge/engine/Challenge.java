package com.migibert.challenge.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ro.fortsoft.pf4j.ExtensionPoint;

public interface Challenge extends ExtensionPoint {
    String getId();

    String getTitle();

    String getStatement();

    ChallengeContract getContract();

    @JsonIgnore
    ChallengeTestSuite getTestSuite();

    boolean isActive();

    void setActive(boolean active);
}
