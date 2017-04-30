package com.migibert.challenge.controller;

import com.google.common.base.Strings;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.engine.Engine;
import com.migibert.challenge.engine.Score;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ChallengerService;
import com.migibert.challenge.service.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
        if(Strings.isNullOrEmpty(name)) {
            return ResponseEntity.badRequest().build();
        }
        Challenger result = service.getChallenger(name);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/challengers/{name}")
    public ResponseEntity<?> deleteChallenger(@PathVariable String name) {
        if(Strings.isNullOrEmpty(name)) {
            return ResponseEntity.badRequest().build();
        }
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
        if(Strings.isNullOrEmpty(name)) {
            return ResponseEntity.badRequest().build();
        }
        if(service.getChallenger(name) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerTotalScore(name));
    }

    @GetMapping(value = "/challengers/{name}/scores/{challengeId}")
    public ResponseEntity<?> getScores(@PathVariable String name, @PathVariable String challengeId) {
        if(Strings.isNullOrEmpty(name)) {
            return ResponseEntity.badRequest().build();
        }
        if(Strings.isNullOrEmpty(challengeId)) {
            return ResponseEntity.badRequest().build();
        }
        if(service.getChallenger(name) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerScoreAtChallenge(name, challengeId));
    }
}