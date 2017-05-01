package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;

import java.util.Date;

public class ChallengeDeactivatedEvent {
    private Date date;
    private Challenge challenge;

    public ChallengeDeactivatedEvent(Challenge challenge) {
        this.date = new Date();
        this.challenge = challenge;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Challenge getChallenge() {
        return challenge;
    }
}
