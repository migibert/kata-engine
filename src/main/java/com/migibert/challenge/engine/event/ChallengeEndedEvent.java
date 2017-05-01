package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;

import java.util.Date;

public class ChallengeEndedEvent {
    private Date date;
    private Challenge challenge;

    public ChallengeEndedEvent(final Challenge challenge) {
        this.date = new Date();
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}

