package com.migibert.challenge.engine;

import com.migibert.challenge.hash.md5.Md5Challenge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Registry {

    private List<Challenger> challengers = new ArrayList<Challenger>();
    private List<Challenge> challenges = new ArrayList<Challenge>();

    public Registry() {
        loadChallenges();
    }

    private void loadChallenges() {
        this.challenges.add(new Md5Challenge());
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void registerChallenger(Challenger challenger) {
        this.challengers.add(challenger);
    }

    public List<Challenger> listChallengers() {
        return this.challengers;
    }

    public Challenger getChallenger(String name) {
        for(Challenger challenger : challengers) {
            if(challenger.getName().equals(name)) {
                return challenger;
            }
        }
        return null;
    }

    public void deleteChallenger(String name) {
        Challenger toDelete = null;
        for(Challenger challenger : challengers) {
            if(challenger.getName().equals(name)) {
                toDelete = challenger;
            }
        }
        if(toDelete != null) {
            challengers.remove(toDelete);
        }
    }

    public List<Challenge> getActiveChallenges() {
        return challenges;
    }

    public List<Challenger> getActiveChallengers() {
        return challengers;
    }

    public Challenge getChallenge(String id) {
        for(Challenge challenge : challenges) {
            if(challenge.getId().equals(id)) {
                return challenge;
            }
        }
        return null;
    }
}
