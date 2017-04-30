package com.migibert.challenge.service;

import com.google.common.collect.Iterables;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.hash.md5.Md5Challenge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallengerService {

    private List<Challenger> challengers = new ArrayList<>();

    public void registerChallenger(Challenger challenger) {
        this.challengers.add(challenger);
    }

    public List<Challenger> listChallengers() {
        return this.challengers;
    }

    public Challenger getChallenger(String name) {
        return Iterables.tryFind(challengers, challenger -> challenger.getName().equals(name)).orNull();
    }

    public boolean deleteChallenger(String name) {
        Challenger toDelete = getChallenger(name);
        return challengers.remove(toDelete);
    }

    public Iterable<Challenger> getActiveChallengers() {
        return Iterables.filter(challengers, challenger -> challenger.isActive());
    }

}
