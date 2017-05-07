package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.registry.ChallengerRegisteredEvent;
import com.migibert.challenge.event.registry.ChallengerUnregisteredEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChallengerService {

    private List<Challenger> challengers = new ArrayList<>();

    @Inject
    private EventBus bus;

    public void registerChallenger(Challenger challenger) {
        bus.post(new ChallengerRegisteredEvent(challenger));
        this.challengers.add(challenger);
    }

    public List<Challenger> listChallengers() {
        return this.challengers;
    }

    public Optional<Challenger> getChallenger(String name) {
        return challengers.stream().filter(challenger -> challenger.getName().equals(name)).findFirst();
    }

    public void unregisterChallenger(String name) {
        this.challengers.removeIf(challenger -> challenger.getName().equals(name));
        bus.post(new ChallengerUnregisteredEvent(name));
    }

}
