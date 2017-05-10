package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.engine.Score;
import com.migibert.challenge.event.scoring.ChallengerScoringEndedEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {
    @Mock
    private EventBus bus;

    @InjectMocks
    private ScoreService service;

    @Test
    public void get_non_existing_game_scores() {
        List<Score> scores = service.getGameScores("test");
        assertEquals(0, scores.size());
    }

    @Test
    public void get_non_existing_challenger_game_scores() {
        List<Score> scores = service.getChallengerScoreAtGame("test", "test");
        assertEquals(0, scores.size());
    }

    @Test
    public void get_existing_game_scores() {
        Score score = new Score("gameId", 0, "challenge", "challenger");
        ChallengerScoringEndedEvent scoringEndedEvent = new ChallengerScoringEndedEvent("gameId", score);

        service.storeScore(scoringEndedEvent);

        List<Score> scores = service.getGameScores("gameId");
        assertEquals(1, scores.size());
        assertTrue(scores.contains(score));
    }


    @Test
    public void get_existing_challenger_game_score() {
        Score score = new Score("gameId", 0, "challenge", "challenger");
        ChallengerScoringEndedEvent scoringEndedEvent = new ChallengerScoringEndedEvent("gameId", score);

        service.storeScore(scoringEndedEvent);

        List<Score> scores = service.getChallengerScoreAtGame("challenger", "gameId");
        assertEquals(1, scores.size());
        assertTrue(scores.contains(score));
    }

    @Test
    public void get_existing_game_scores_when_different_games_have_been_scored() {
        Score score1 = new Score("gameId", 0, "challenge", "challenger");
        Score score2 = new Score("another", 0, "challenge", "challenger");
        Score score3 = new Score("gameId", 0, "challenge", "challenger");
        ChallengerScoringEndedEvent scoringEndedEvent1 = new ChallengerScoringEndedEvent("gameId", score1);
        ChallengerScoringEndedEvent scoringEndedEvent2 = new ChallengerScoringEndedEvent("another", score2);
        ChallengerScoringEndedEvent scoringEndedEvent3 = new ChallengerScoringEndedEvent("gameId", score3);

        service.storeScore(scoringEndedEvent1);
        service.storeScore(scoringEndedEvent2);
        service.storeScore(scoringEndedEvent3);

        List<Score> scores = service.getGameScores("gameId");
        assertEquals(2, scores.size());
        assertTrue(scores.contains(score1));
        assertFalse(scores.contains(score2));
        assertTrue(scores.contains(score3));
    }

    @Test
    public void get_existing_challenger_game_scores_when_different_games_have_been_scored() {
        Score score1 = new Score("gameId", 0, "challenge", "challenger");
        Score score2 = new Score("another", 0, "challenge", "guy");
        Score score3 = new Score("gameId", 0, "challenge", "challenger");
        ChallengerScoringEndedEvent scoringEndedEvent1 = new ChallengerScoringEndedEvent("gameId", score1);
        ChallengerScoringEndedEvent scoringEndedEvent2 = new ChallengerScoringEndedEvent("another", score2);
        ChallengerScoringEndedEvent scoringEndedEvent3 = new ChallengerScoringEndedEvent("gameId", score3);

        service.storeScore(scoringEndedEvent1);
        service.storeScore(scoringEndedEvent2);
        service.storeScore(scoringEndedEvent3);

        List<Score> scores = service.getChallengerScoreAtGame("challenger", "gameId");
        assertEquals(2, scores.size());
        assertTrue(scores.contains(score1));
        assertFalse(scores.contains(score2));
        assertTrue(scores.contains(score3));
    }
}
