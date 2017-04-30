package com.migibert.challenge.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.migibert.challenge.engine.Score;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ScoreService {
    private List<Score> scores = new ArrayList<>();

    public void register(Collection<Score> scores) {
        this.scores.addAll(scores);
    }

    public List<Score> getChallengeScore(String challengeId) {
        return Lists.newArrayList(Iterables.filter(scores, score -> score.getChallenge().getId().equals(challengeId)));
    }

    public List<Score> getChallengerScore(String challengerName) {
        return Lists.newArrayList(Iterables.filter(scores, score -> score.getChallenger().getName().equals(challengerName)));
    }

    public List<Score> getChallengerScoreAtChallenge(String challengerName, String challengeId) {
        return Lists.newArrayList(Iterables.filter(scores, score -> score.getChallenger().getName().equals(challengerName) && score.getChallenge().getId().equals(challengeId)))    ;
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
