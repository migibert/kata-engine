package com.migibert.challenge.event.game;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.ChallengeTestSuite;
import com.migibert.challenge.engine.Challenger;

import java.util.Date;

public class ChallengerTestSuiteStartedEvent extends GameEvent {
    private Challenger challenger;
    private Challenge challenge;

    public ChallengerTestSuiteStartedEvent(String gameId, Challenge challenge, Challenger challenger) {
        super(gameId);
        this.challenge = challenge;
        this.challenger = challenger;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Challenger getChallenger() {
        return challenger;
    }
}
