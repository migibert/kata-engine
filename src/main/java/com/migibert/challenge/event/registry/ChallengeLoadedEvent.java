package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.event.EngineEvent;

import java.util.Date;

public class ChallengeLoadedEvent extends EngineEvent {
    private Challenge challenge;

    public ChallengeLoadedEvent(Challenge challenge) {
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }
}
