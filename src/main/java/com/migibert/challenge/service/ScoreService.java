package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.migibert.challenge.engine.*;
import com.migibert.challenge.event.game.ChallengerTestSuiteEndedEvent;
import com.migibert.challenge.event.scoring.ChallengerScoringEndedEvent;
import com.migibert.challenge.event.scoring.ChallengerScoringStartedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ScoreService {
    private List<Score> scores = new ArrayList<>();
    private List<ScoreScheme> schemes = new ArrayList<>();
    private EventBus bus;

    @Inject
    public ScoreService(EventBus bus) {
        bus.register(this);
        this.bus = bus;
    }

    public void register(Score score) {
        this.scores.add(score);
    }

    public List<Score> getChallengeScore(String challengeId) {
        return scores.parallelStream().filter(score -> score.getChallenge().getId().equals(challengeId)).collect(Collectors.toList());
    }

    public List<Score> getChallengerScores(String challengerName) {
        return scores.parallelStream().filter(score -> score.getChallenger().getName().equals(challengerName)).collect(Collectors.toList());
    }

    public List<Score> getChallengerScoresAtChallenge(String challengerName, String challengeId) {
        return getChallengerScoresAtChallengeAsStream(challengerName, challengeId).collect(Collectors.toList());
    }

    public int getChallengerTotalScore(String challengerName) {
        return scores.parallelStream()
                .filter(score -> score.getChallenger().getName().equals(challengerName))
                .mapToInt(Score::getScore)
                .sum();
    }

    public int getChallengerTotalScoreAtChallenge(String challengerName, String challengeId) {
        return scores.parallelStream()
                .filter(score -> score.getChallenger().getName().equals(challengerName))
                .filter(score -> score.getChallenge().getId().equals(challengeId))
                .mapToInt(Score::getScore)
                .sum();
    }

    private Stream<Score> getChallengerScoresAtChallengeAsStream(String challengerName, String challengeId) {
        return scores.parallelStream()
                .filter(score -> score.getChallenger().getName().equals(challengerName))
                .filter(score -> score.getChallenge().getId().equals(challengeId));
    }

    public Optional<ScoreScheme> getChallengeScoreScheme(Challenge challenge) {
        Optional<ScoreScheme> scheme = schemes.stream()
                .filter(scoringScheme -> scoringScheme.getChallenge().getId().equals(challenge.getId()))
                .findFirst();

        ScoreScheme defaultScheme = new ScoreScheme(challenge, 5, 2, 0);
        if (!scheme.isPresent()) {
            schemes.add(defaultScheme);
            return Optional.of(defaultScheme);
        }
        return scheme;
    }

    public ScoreScheme createChallengeScoreScheme(Challenge challenge, int successScore, int partialSuccessScore, int failureScore) {
        ScoreScheme scheme = new ScoreScheme(challenge, successScore, partialSuccessScore, failureScore);
        this.schemes.add(scheme);
        return scheme;
    }

    public ScoreScheme createDefaultChallengeScoreScheme(Challenge challenge) {
        return createDefaultChallengeScoreScheme(challenge);
    }

    @Subscribe
    public void score(ChallengerTestSuiteEndedEvent event) {
        String gameId = event.getGameId();
        Challenger challenger = event.getChallenger();
        Challenge challenge = event.getChallenge();
        ChallengeTestSuiteResult result = event.getResult();

        bus.post(new ChallengerScoringStartedEvent(gameId, challenger, challenge, result));
        ScoreScheme scheme = getChallengeScoreScheme(challenge).get();
        int score = 0;
        if (event.getResult().getSuccesses() == 0) {
            score = scheme.getFailureScore();
        } else if (result.getFailures() == 0) {
            score = scheme.getSuccessScore();
        } else {
            score = scheme.getPartialSuccessScore();
        }
        bus.post(new ChallengerScoringEndedEvent(gameId, new Score(score, challenge, challenger)));
    }

    @Subscribe
    public void storeScore(ChallengerScoringEndedEvent event) {
        register(event.getScore());
    }
}

