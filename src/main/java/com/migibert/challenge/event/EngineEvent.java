package com.migibert.challenge.event;

import java.util.Date;

public class EngineEvent {
    private Date date;

    public EngineEvent() {
        this.date = new Date();
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
