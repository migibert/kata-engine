package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;

import java.util.Date;

public class ChallengerEvaluationStartedEvent {
    private Date date;
    private Challenger challenger;
    private Challenge challenge;
    private int successes;
    private int failures;


    public ChallengerEvaluationStartedEvent(Challenger challenger, Challenge challenge, int successes, int failures) {
        this.date = new Date();
        this.challenger = challenger;
        this.challenge = challenge;
        this.successes = successes;
        this.failures = failures;
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

    public int getSuccesses() {
        return successes;
    }

    public int getFailures() {
        return failures;
    }
}
