package com.migibert.challenge.event.game;

import com.migibert.challenge.engine.Challenge;

public class ChallengeEndedEvent extends GameEvent {
    private Challenge challenge;

    public ChallengeEndedEvent(String gameId, final Challenge challenge) {
        super(gameId);
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }
}

