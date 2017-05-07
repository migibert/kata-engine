package com.migibert.challenge.engine;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private String title;
    private String id = UUID.randomUUID().toString();
    private List<Challenger> challengers = new ArrayList<>();
    private List<Challenge> challenges = new ArrayList<>();
    private ScoreScheme scoreScheme;
    private boolean active;

    public Game(String title, ScoreScheme scoreScheme) {
        this.title = title;
        this.scoreScheme = scoreScheme;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Challenge> getChallenges() {
        return ImmutableList.copyOf(challenges);
    }

    public List<Challenger> getChallengers() {
        return ImmutableList.copyOf(challengers);
    }

    public void addChallenge(Challenge challenge) {
        this.challenges.add(challenge);
    }

    public void removeChallenge(Challenge challenge) {
        this.challenges.remove(challenge);
    }

    public void addChallenger(Challenger challenger) {
        this.challengers.add(challenger);
    }

    public void removeChallenger(Challenger challenger) {
        this.challengers.remove(challenger);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ScoreScheme getScoreScheme() {
        return scoreScheme;
    }
}