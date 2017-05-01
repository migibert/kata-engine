package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;

import java.util.Date;

/**
 * Created by mika on 02/05/17.
 */
public class ChallengeActivatedEvent {
    private Date date;
    private Challenge challenge;

    public ChallengeActivatedEvent(Challenge challenge) {
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
