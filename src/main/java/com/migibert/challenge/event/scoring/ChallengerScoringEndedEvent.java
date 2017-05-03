package com.migibert.challenge.event.scoring;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.engine.Score;
import com.migibert.challenge.event.game.GameEvent;

import java.util.Date;

public class ChallengerScoringEndedEvent extends GameEvent {
    private Score score;

    public ChallengerScoringEndedEvent(String gameId, Score score) {
        super(gameId);
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
