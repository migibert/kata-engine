package com.migibert.challenge.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.migibert.challenge.engine.Score;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreService {
    private List<Score> scores = new ArrayList<>();

    public void register(Iterable<Score> score) {
        this.scores.addAll(scores);
    }

    public Iterable<Score> getChallengeScore(String challengeId) {
        return Iterables.filter(scores, score -> score.getChallenge().getId().equals(challengeId));
    }

    public Iterable<Score> getChallengerScore(String challengerName) {
        return Iterables.filter(scores, score -> score.getChallenger().getName().equals(challengerName));
    }

    public Iterable<Score> getChallengerScoreAtChallenge(String challengerName, String challengeId) {
        return Iterables.filter(scores, score -> score.getChallenger().getName().equals(challengerName) && score.getChallenge().getId().equals(challengeId));
    }

    public int getChallengerTotalScore(String challengerName) {
        int total = 0;
        for(Score score : getChallengerScore(challengerName)) {
            total += score.getScore();
        }
        return total;
    }

    public int getChallengerTotalScoreAtChallenge(String challengerName, String challengeId) {
        int total = 0;
        for(Score score : getChallengerScoreAtChallenge(challengerName, challengeId)) {
            total += score.getScore();
        }
        return total;
    }
}
