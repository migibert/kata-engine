package com.migibert.challenge.event.game;

import com.migibert.challenge.event.GameEvent;

public class GameEndedEvent extends GameEvent {

    public GameEndedEvent(String gameId) {
        super(gameId);
    }

}
