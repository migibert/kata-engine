package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;

import java.util.Date;

public class ChallengerEvaluationEndedEvent {
    private Date date;
    private Challenger challenger;
    private Challenge challenge;
    private int score;


    public ChallengerEvaluationEndedEvent(Challenger challenger, Challenge challenge, int score) {
        this.date = new Date();
        this.challenger = challenger;
        this.challenge = challenge;
        this.score = score;
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

    public int getScore() {
        return score;
    }
}
