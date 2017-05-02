package com.migibert.challenge.event.game;

import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.ChallengeTestResult;
import com.migibert.challenge.engine.Challenger;

public class ChallengerTestEndedEvent extends GameEvent {

    private ChallengeTest test;
    private Challenger challenger;
    private ChallengeTestResult result;

    public ChallengerTestEndedEvent(String gameId, ChallengeTest test, Challenger challenger, ChallengeTestResult result) {
        super(gameId);
        this.test = test;
        this.challenger = challenger;
        this.result = result;
    }

    public ChallengeTest getTest() {
        return test;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    public ChallengeTestResult getResult() {
        return result;
    }
}
