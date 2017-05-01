package com.migibert.challenge.engine.event;

import com.migibert.challenge.engine.Challenger;

import java.util.Date;

public class ChallengerRegisteredEvent {
    private Date date;
    private Challenger challenger;

    public ChallengerRegisteredEvent(Challenger challenger) {
        this.challenger = new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
        this.date = new Date();
    }

    public Challenger getChallenger() {
        return new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
