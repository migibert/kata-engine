package com.migibert.challenge.controller;

import com.migibert.challenge.engine.Game;
import com.migibert.challenge.service.GameService;
import com.migibert.challenge.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import java.util.Optional;

public class GameController {

    @Inject
    private ScoreService scoreService;

    @Inject
    private GameService gameService;

    @GetMapping(value = "/games/{id}/scores")
    public ResponseEntity<?> getScores(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scoreService.getGameScores(id));
    }

    @GetMapping(value = "/games/{id}/scores/{challengerName}")
    public ResponseEntity<?> getChallengerGameScores(@PathVariable String id, @PathVariable String challengerName) {
        if (StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        if (StringUtils.isEmpty(challengerName)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerScoreAtGame(challengerName, id));
    }

    @PostMapping(value = "/games")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        return ResponseEntity.ok(this.gameService.create(game));
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<?> getGame(String id) {
        if(StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Game> result = this.gameService.get(id);
        if(!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }


    @GetMapping(value = "/games/{id}")
    public ResponseEntity<?> deleteGame(String id) {
        if(StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Game> result = this.gameService.get(id);
        if(!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        this.gameService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
