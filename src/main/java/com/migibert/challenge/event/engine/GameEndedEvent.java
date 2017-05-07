package com.migibert.challenge.event.engine;

import com.migibert.challenge.event.GameEvent;

public class GameEndedEvent extends GameEvent {

    public GameEndedEvent(String gameId) {
        super(gameId);
    }

}
