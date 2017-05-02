package com.migibert.challenge.event.game;

import com.migibert.challenge.event.EngineEvent;

public class GameEvent extends EngineEvent {
    private String gameId;

    public GameEvent(String gameId) {
        super();
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
}

