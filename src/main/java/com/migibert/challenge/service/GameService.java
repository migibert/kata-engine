package com.migibert.challenge.service;

import com.google.common.collect.ImmutableList;
import com.migibert.challenge.engine.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GameService {
    private List<Game> games = new ArrayList<>();

    @Inject
    private Engine engine;

    public Game create(Game game) {
        this.games.add(game);
        return game;
    }

    public Optional<Game> get(String gameId) {
        return games.stream().filter(game -> game.getId().equals(gameId)).findFirst();
    }


    public void remove(String id) {
        games.removeIf(game -> game.getId().equals(id));
    }

    public List<Game> getGames() {
        return ImmutableList.copyOf(games);
    }

    public List<Game> getChallengerGames(String challengerId) {
        return games.stream()
                .filter(game -> game.getChallengers().stream()
                        .filter(challenger -> challenger.getName().equals(challengerId))
                        .findFirst()
                        .isPresent())
                .collect(Collectors.toList());
    }

    public List<Game> getChallengeGames(String challengeId) {
        return games.stream()
                .filter(game -> game.getChallenges().stream()
                        .filter(challenge -> challenge.getId().equals(challengeId))
                        .findFirst()
                        .isPresent())
                .collect(Collectors.toList());
    }

    public boolean addChallengeToGame(String gameId, Challenge challenge) {
        Optional<Game> result = get(gameId);
        if(!result.isPresent()) {
            return false;
        }
        Game game = result.get();
        game.addChallenge(challenge);
        return true;
    }

    public boolean addChallengerToGame(String gameId, Challenger challenger) {
        Optional<Game> result = get(gameId);
        if(!result.isPresent()) {
            return false;
        }
        Game game = result.get();
        game.addChallenger(challenger);
        return true;
    }

    public boolean activate(String gameId) {
        return setActive(gameId, true);
    }

    public boolean deactivate(String gameId) {
        return setActive(gameId, false);
    }

    private boolean setActive(String gameId, boolean active) {
        Optional<Game> result = get(gameId);
        if(!result.isPresent()) {
            return false;
        }
        Game game = result.get();
        game.setActive(active);
        return true;
    }

    @Scheduled(fixedDelayString = "${game.loop.delay}")
    public void play() {
        games.stream().filter(game -> game.isActive()).forEach(game -> engine.play(game));
    }

}
