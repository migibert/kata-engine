package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.event.EngineEvent;

public class ChallengeDeactivatedEvent extends EngineEvent {
    private Challenge challenge;

    public ChallengeDeactivatedEvent(Challenge challenge) {
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }
}
