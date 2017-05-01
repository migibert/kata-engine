package com.migibert.challenge.controller;

import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.engine.Engine;
import com.migibert.challenge.service.ChallengerService;
import com.migibert.challenge.service.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Controller
public class ChallengerController {

    @Inject
    private Engine engine;

    @Inject
    private ChallengerService service;

    @Inject
    private ScoreService scoreService;

    @PostMapping(value = "/challengers")
    public ResponseEntity<?> createChallenger(@RequestBody Challenger challenger) {
        service.registerChallenger(challenger);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/challengers")
    public ResponseEntity<?> getChallengers() {
        List<Challenger> result = service.listChallengers();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/challengers/{name}")
    public ResponseEntity<?> getChallenger(@PathVariable String name) {
        Optional<Challenger> result = service.getChallenger(name);
        if(!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/challengers/{name}")
    public ResponseEntity<?> deleteChallenger(@PathVariable String name) {
        if(service.getChallenger(name) == null) {
            return ResponseEntity.notFound().build();
        }
        if(!service.deleteChallenger(name)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/challengers/{name}/scores")
    public ResponseEntity<?> getScores(@PathVariable String name) {
        if(!service.getChallenger(name).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerTotalScore(name));
    }

    @GetMapping(value = "/challengers/{name}/scores/{challengeId}")
    public ResponseEntity<?> getScores(@PathVariable String name, @PathVariable String challengeId) {
        if(!service.getChallenger(name).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerScoresAtChallenge(name, challengeId));
    }
}