package com.migibert.challenge.event.game;

import com.google.common.collect.ImmutableList;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.GameEvent;

import java.util.List;
import java.util.Objects;

public class GameStartedEvent extends GameEvent {
    private ImmutableList<Challenger> challengers;
    private ImmutableList<Challenge> challenges;


    public GameStartedEvent(String gameId, List<Challenger> activeChallengers, List<Challenge> activeChallenges) {
        super(gameId);
        this.challengers = ImmutableList.copyOf(activeChallengers);
        this.challenges = ImmutableList.copyOf(activeChallenges);
    }

    public ImmutableList<Challenge> getChallenges() {
        return challenges;
    }

    public ImmutableList<Challenger> getChallengers() {
        return challengers;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(challengers, challenges);
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
        final GameStartedEvent other = (GameStartedEvent) obj;
        return Objects.equals(this.challengers, other.challengers)
                && Objects.equals(this.challenges, other.challenges);
    }
}
