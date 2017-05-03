package com.migibert.challenge.event.game;

import com.google.common.collect.ImmutableList;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;

import java.util.List;

public class GameStartedEvent extends GameEvent {
    private ImmutableList<Challenger> challengers;
    private ImmutableList<Challenge> challenges;


    public GameStartedEvent(String gameId, List<Challenger> activeChallengers, List<Challenge> activeChallenges) {
        super(gameId);
        this.challengers = ImmutableList.copyOf(activeChallengers);
        this.challenges = ImmutableList.copyOf(activeChallenges);
    }

    public ImmutableList<Challenge> getChallenges() {
        return challenges;
    }

    public ImmutableList<Challenger> getChallengers() {
        return challengers;
    }
}
