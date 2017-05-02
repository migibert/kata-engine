package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Engine;
import com.migibert.challenge.event.EngineEvent;

import java.util.Date;

public class ChallengeActivatedEvent extends EngineEvent {
    private Challenge challenge;

    public ChallengeActivatedEvent(Challenge challenge) {
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }
}
