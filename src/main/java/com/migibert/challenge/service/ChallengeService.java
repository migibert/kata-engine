package com.migibert.challenge.service;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.hash.md5.Md5Challenge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Challenge> getActiveChallenges() {
        return challenges.stream().filter(challenge -> challenge.isActive()).collect(Collectors.toList());
    }

    public Optional<Challenge> getChallenge(String id) {
        return challenges.stream().filter(challenge -> challenge.getId().equals(id)).findFirst();
    }

    public boolean deleteChallenge(String id) {
        Optional<Challenge> challenge = getChallenge(id);
        if(!challenge.isPresent()) {
            return false;
        }
        return challenges.remove(challenge.get());
    }

    public boolean activate(String id) {
        Optional<Challenge> challenge = getChallenge(id);
        if(!challenge.isPresent()) {
            return false;
        }
        challenge.get().setActive(true);
        return true;
    }

    public boolean deactivate(String id) {
        Optional<Challenge> challenge = getChallenge(id);
        if(!challenge.isPresent()) {
            return false;
        }
        challenge.get().setActive(false);
        return true;
    }
}
