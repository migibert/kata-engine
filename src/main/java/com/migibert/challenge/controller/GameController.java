package com.migibert.challenge.controller;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.engine.Game;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ChallengerService;
import com.migibert.challenge.service.GameService;
import com.migibert.challenge.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

@Controller
public class GameController {

    @Inject
    private ScoreService scoreService;

    @Inject
    private GameService gameService;

    @Inject
    private ChallengeService challengeService;

    @Inject
    private ChallengerService challengerService;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(this.gameService.create(game));
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<?> getGame(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Game> result = this.gameService.get(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping(value = "/games")
    public ResponseEntity<?> getGames() {
        return ResponseEntity.ok(gameService.getGames());
    }

    @DeleteMapping(value = "/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Game> result = this.gameService.get(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        this.gameService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/games/{id}/challenges/{challengeId}")
    public ResponseEntity<?> addChallengeToGame(@PathVariable String id, @PathVariable String challengeId) {
        Optional<Game> gameResult = gameService.get(id);
        if (!gameResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Challenge> challengeResult = challengeService.getChallenge(challengeId);
        if (!challengeResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Game game = gameResult.get();
        Challenge challenge = challengeResult.get();
        if (game.getChallenges().contains(challenge)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        game.addChallenge(challenge);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/games/{id}/challengers/{challengerId}")
    public ResponseEntity<?> addChallengerToGame(@PathVariable String id, @PathVariable String challengerId) {
        Optional<Game> gameResult = gameService.get(id);
        if (!gameResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Challenger> challengerResult = challengerService.getChallenger(challengerId);
        if (!challengerResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Game game = gameResult.get();
        Challenger challenger = challengerResult.get();
        if (game.getChallengers().contains(challenger)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        game.addChallenger(challenger);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/games/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable String id) {
        Optional<Game> gameResult = gameService.get(id);
        if (!gameResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        gameService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/games/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable String id) {
        Optional<Game> gameResult = gameService.get(id);
        if (!gameResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        gameService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
