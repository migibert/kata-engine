package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;

import java.util.Date;

public class ChallengeStartedEvent {
    private Date date;
    private Challenge challenge;

    public ChallengeStartedEvent(Challenge challenge) {
        this.challenge = challenge;
        this.date = new Date();
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
