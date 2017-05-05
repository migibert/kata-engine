package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.event.registry.ChallengeActivatedEvent;
import org.springframework.stereotype.Component;
import ro.fortsoft.pf4j.PluginManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChallengeService {

    private List<Challenge> challenges = new ArrayList<>();

    @Inject
    private EventBus bus;

    @Inject
    public ChallengeService(PluginManager pluginManager) {
        List<Challenge> extensions = pluginManager.getExtensions(Challenge.class);
        this.challenges.addAll(extensions);
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

    public boolean activate(String id) {
        Optional<Challenge> challenge = getChallenge(id);
        if (!challenge.isPresent()) {
            return false;
        }
        Challenge result = challenge.get();
        result.setActive(true);
        bus.post(new ChallengeActivatedEvent(result));
        return true;
    }

    public boolean deactivate(String id) {
        Optional<Challenge> challenge = getChallenge(id);
        if (!challenge.isPresent()) {
            return false;
        }
        Challenge result = challenge.get();
        result.setActive(false);
        bus.post(new ChallengeActivatedEvent(result));
        return true;
    }
}
