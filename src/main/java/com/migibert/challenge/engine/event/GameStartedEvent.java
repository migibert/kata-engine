package com.migibert.challenge.engine.event;

import java.util.Date;

public class GameStartedEvent {
    private Date date;

    public GameStartedEvent() {
        this.date = new Date();
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
