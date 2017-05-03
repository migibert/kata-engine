package com.migibert.challenge.event.game;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.ChallengeTestSuiteResult;
import com.migibert.challenge.engine.Challenger;

public class ChallengerTestSuiteEndedEvent extends GameEvent {
    private Challenger challenger;
    private Challenge challenge;
    private ChallengeTestSuiteResult result;

    public ChallengerTestSuiteEndedEvent(String gameId, Challenge challenge, Challenger challenger, ChallengeTestSuiteResult result) {
        super(gameId);
        this.challenge = challenge;
        this.challenger = challenger;
        this.result = result;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    public ChallengeTestSuiteResult getResult() {
        return result;
    }
}
