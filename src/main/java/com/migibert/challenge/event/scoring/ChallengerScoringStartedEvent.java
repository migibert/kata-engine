package com.migibert.challenge.event.scoring;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.ChallengeTestSuiteResult;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.game.GameEvent;

public class ChallengerScoringStartedEvent extends GameEvent {
    private Challenger challenger;
    private Challenge challenge;
    private ChallengeTestSuiteResult result;

    public ChallengerScoringStartedEvent(String gameId, Challenger challenger, Challenge challenge, ChallengeTestSuiteResult result) {
        super(gameId);
        this.challenger = challenger;
        this.challenge = challenge;
        this.result = result;
    }

    public Challenger getChallenger() {
        return new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public ChallengeTestSuiteResult getResult() {
        return result;
    }
}
