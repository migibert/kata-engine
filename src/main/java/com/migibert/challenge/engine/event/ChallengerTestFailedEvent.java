package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.Challenger;

import java.util.Date;

public class ChallengerTestFailedEvent {
    private Date date;
    private Challenger challenger;
    private Challenge challenge;
    private ChallengeTest test;

    public ChallengerTestFailedEvent(Challenger challenger, Challenge challenge, ChallengeTest test) {
        this.date = new Date();
        this.challenger = challenger;
        this.challenge = challenge;
        this.test = test;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Challenger getChallenger() {
        return new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public ChallengeTest getTest() {
        return test;
    }
}
