package com.migibert.challenge.plugins.md5;

import com.google.common.collect.Lists;
import com.migibert.challenge.engine.AbstractChallenge;
import com.migibert.challenge.engine.ChallengeContract;
import com.migibert.challenge.engine.ChallengeTestSuite;
import ro.fortsoft.pf4j.Extension;

@Extension
public class Md5Challenge extends AbstractChallenge {
    private ChallengeContract contract = new Md5ChallengeContract();

    @Override
    public String getId() {
        return "MD5";
    }

    @Override
    public String getTitle() {
        return "MD5";
    }

    @Override
    public String getStatement() {
        return "Implement MD5 hash function as fast as possible";
    }

    @Override
    public ChallengeContract getContract() {
        return contract;
    }

    @Override
    public ChallengeTestSuite getTestSuite() {
        return new ChallengeTestSuite(new Md5BasicChallengeTest());
    }
}
