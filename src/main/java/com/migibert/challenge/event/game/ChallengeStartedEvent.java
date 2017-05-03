package com.migibert.challenge.event.game;

import com.google.common.collect.ImmutableList;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;

import java.util.List;

public class ChallengeStartedEvent extends GameEvent{
    private Challenge challenge;
    private ImmutableList<Challenger> challengers;

    public ChallengeStartedEvent(String gameId, Challenge challenge, List<Challenger> challengers) {
        super(gameId);
        this.challenge = challenge;
        this.challengers = ImmutableList.copyOf(challengers);
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public ImmutableList<Challenger> getChallengers() {
        return challengers;
    }
}
