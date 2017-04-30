package com.migibert.challenge.service;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.hash.md5.Md5Challenge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallengeService {

    private List<Challenge> challenges = new ArrayList<>();

    public ChallengeService() {
        loadChallenges();
    }

    private void loadChallenges() {
        this.challenges.add(new Md5Challenge());
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public Iterable<Challenge> getActiveChallenges() {
        return Iterables.filter(challenges, challenge -> challenge.isActive());
    }

    public Challenge getChallenge(String id) {
        return Iterables.tryFind(challenges, challenge -> challenge.getId().equals(id)).orNull();
    }

    public boolean deleteChallenge(String id) {
        Challenge challenge = getChallenge(id);
        return challenges.remove(challenge);
    }
}
