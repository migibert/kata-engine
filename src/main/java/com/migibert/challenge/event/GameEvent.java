package com.migibert.challenge.event;

import java.util.Objects;

public class GameEvent extends EngineEvent {
    private String gameId;

    public GameEvent(String gameId) {
        super();
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(gameId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final GameEvent other = (GameEvent) obj;
        return Objects.equals(this.gameId, other.gameId);
    }
}

