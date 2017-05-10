package com.migibert.challenge.service;

import com.migibert.challenge.engine.Challenge;
import org.springframework.stereotype.Component;
import ro.fortsoft.pf4j.PluginManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChallengeService {

    private List<Challenge> challenges = new ArrayList<>();

    @Inject
    public ChallengeService(PluginManager pluginManager) {
        List<Challenge> extensions = pluginManager.getExtensions(Challenge.class);
        this.challenges.addAll(extensions);
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public Optional<Challenge> getChallenge(String id) {
        return challenges.stream().filter(challenge -> challenge.getId().equals(id)).findFirst();
    }
}
