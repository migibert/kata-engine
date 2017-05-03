package com.migibert.challenge.event.registry;

import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.EngineEvent;

import java.util.Date;

public class ChallengerUnregisteredEvent extends EngineEvent{
    private Challenger challenger;

    public ChallengerUnregisteredEvent(Challenger challenger) {
        this.challenger = new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
    }

    public Challenger getChallenger() {
        return new Challenger(challenger.getName(), challenger.getBaseUrl(), challenger.isActive());
    }
}
