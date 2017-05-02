package com.migibert.challenge.event.game;

import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.Challenger;

public class ChallengerTestStartedEvent extends GameEvent {

    private ChallengeTest test;
    private Challenger challenger;

    public ChallengerTestStartedEvent(String gameId, ChallengeTest test, Challenger challenger) {
        super(gameId);
        this.test = test;
        this.challenger = challenger;
    }

    public ChallengeTest getTest() {
        return test;
    }

    public Challenger getChallenger() {
        return challenger;
    }
}
