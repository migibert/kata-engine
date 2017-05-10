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

@Component
public class ChallengerService {

    private List<Challenger> challengers = new ArrayList<>();

    @Inject
    private EventBus bus;

    public boolean registerChallenger(Challenger challenger) {
        if(challengers.contains(challenger)) {
            return false;
        }
        if(challengers.stream().filter(c -> c.getName().equals(challenger.getName()) || c.getBaseUrl().equals(challenger.getBaseUrl())).count() != 0) {
            return false;
        }
        this.challengers.add(challenger);
        bus.post(new ChallengerRegisteredEvent(challenger));
        return true;
    }

    public List<Challenger> listChallengers() {
        return this.challengers;
    }

    public Optional<Challenger> getChallenger(String name) {
        return challengers.stream().filter(challenger -> challenger.getName().equals(name)).findFirst();
    }

    public boolean unregisterChallenger(String name) {
        boolean removed = this.challengers.removeIf(challenger -> challenger.getName().equals(name));
        if(removed) {
            bus.post(new ChallengerUnregisteredEvent(name));
        }
        return removed;
    }

}
