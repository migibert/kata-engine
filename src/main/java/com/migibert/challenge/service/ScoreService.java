package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.migibert.challenge.engine.Score;
import com.migibert.challenge.event.scoring.ChallengerScoringEndedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScoreService {
    private List<Score> scores = new ArrayList<>();
    private EventBus bus;

    @Inject
    public ScoreService(EventBus bus) {
        bus.register(this);
        this.bus = bus;
    }

    public List<Score> getGameScores(String gameId) {
        return scores.stream()
                .filter(score -> score.getGameId().equals(gameId))
                .collect(Collectors.toList());
    }

    public List<Score> getChallengerScoreAtGame(String challengerName, String gameId) {
        return scores.stream()
                .filter(score -> score.getGameId().equals(gameId))
                .filter(score -> score.getChallengerId().equals(challengerName))
                .collect(Collectors.toList());
    }

    @Subscribe
    public void storeScore(ChallengerScoringEndedEvent event) {
        this.scores.add(event.getScore());
    }

}

