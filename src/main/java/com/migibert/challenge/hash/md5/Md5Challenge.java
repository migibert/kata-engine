package com.migibert.challenge.hash.md5;

import com.migibert.challenge.engine.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Md5Challenge extends AbstractChallenge {
    private ChallengeContract contract = new Md5ChallengeContract();
    private RestTemplate template = new RestTemplate();

    public String getTitle() {
        return "MD5";
    }

    public String getStatement() {
        return "Implement MD5 hash function as fast as possible";
    }

    @Override
    public ChallengeContract getContract() {
        return contract;
    }

    @Override
    public List<ChallengeTest> getTests() {
        List<ChallengeTest> tests = new ArrayList<ChallengeTest>();
        tests.add(new Md5BasicChallengeTest());
        return tests;
    }
}
