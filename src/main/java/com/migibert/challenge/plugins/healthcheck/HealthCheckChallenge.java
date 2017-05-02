package com.migibert.challenge.plugins.healthcheck;

import com.google.common.collect.Lists;
import com.migibert.challenge.engine.AbstractChallenge;
import com.migibert.challenge.engine.ChallengeContract;
import com.migibert.challenge.engine.ChallengeTestSuite;
import ro.fortsoft.pf4j.Extension;

@Extension
public class HealthCheckChallenge extends AbstractChallenge {

    @Override
    public String getId() {
        return "Healthcheck";
    }

    @Override
    public String getTitle() {
        return "Healthcheck";
    }

    @Override
    public String getStatement() {
        return "A simple health check to validate challenger app is up and running";
    }

    @Override
    public ChallengeContract getContract() {
        return new HealthCheckChallengeContract();
    }

    @Override
    public ChallengeTestSuite getTestSuite() {
        return new ChallengeTestSuite(new HealthCheckChallengeTest());
    }
}
