package com.migibert.challenge.event.scoring;

import com.migibert.challenge.engine.Score;
import com.migibert.challenge.event.GameEvent;

import java.util.Objects;

public class ChallengerScoringEndedEvent extends GameEvent {
    private Score score;

    public ChallengerScoringEndedEvent(String gameId, Score score) {
        super(gameId);
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(score);
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
        final ChallengerScoringEndedEvent other = (ChallengerScoringEndedEvent) obj;
        return Objects.equals(this.score, other.score);
    }
}
