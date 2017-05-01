package com.migibert.challenge.engine.event;

import java.util.Date;

public class GameEndedEvent {
    private Date date;

    public GameEndedEvent() {
        this.date = new Date();
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
