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
import java.util.stream.Stream;

@Component
public class ScoreService {
    private List<Score> scores = new ArrayList<>();
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

    @Subscribe
    public void onEvaluationEnded(ChallengerScoringEndedEvent event) {
        register(event.getScore());
    }

}

