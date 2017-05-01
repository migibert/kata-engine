package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.engine.event.ChallengerRegisteredEvent;
import com.migibert.challenge.engine.event.ChallengerUnregisteredEvent;
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

    public boolean unregisterChallenger(String name) {
        Optional<Challenger> challenger = getChallenger(name);
        if(!challenger.isPresent()) {
            return false;
        }
        Challenger result = challenger.get();
        bus.post(new ChallengerUnregisteredEvent(result));
        return challengers.remove(result);
    }

    public Iterable<Challenger> getActiveChallengers() {
        return challengers.stream().filter(challenger -> challenger.isActive()).collect(Collectors.toList());
    }

}
