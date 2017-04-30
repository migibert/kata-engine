package com.migibert.challenge.controller;

import com.google.common.base.Strings;
import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Engine;
import com.migibert.challenge.engine.Score;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChallengeController {

    @Inject
    private Engine engine;

    @Inject
    private ChallengeService service;

    @Inject
    private ScoreService scoreService;

    @GetMapping(value = "/challenges")
    public ResponseEntity<?> getChallenges() {
        List<Challenge> result = service.getChallenges();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/challenges/{id}")
    public ResponseEntity<?> getChallenge(@PathVariable String id) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Challenge result = service.getChallenge(id);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/challenges/{id}")
    public ResponseEntity<?> deleteChallenge(@PathVariable String id) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Challenge challenge = service.getChallenge(id);
        if(challenge == null) {
            return ResponseEntity.notFound().build();
        }
        if(!service.deleteChallenge(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/challenges/{id}/activate")
    public ResponseEntity<?> activateChallenge(@PathVariable String id) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Challenge challenge = service.getChallenge(id);
        if(challenge == null) {
            return ResponseEntity.notFound().build();
        }
        challenge.setActive(true);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/challenges/{id}/deactivate")
    public ResponseEntity<?> deactivateChallenge(@PathVariable String id) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Challenge challenge = service.getChallenge(id);
        if(challenge == null) {
            return ResponseEntity.notFound().build();
        }
        challenge.setActive(false);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/challenges/{id}/scores")
    public ResponseEntity<?> getScores(@PathVariable String id) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scoreService.getChallengeScore(id));
    }

    @GetMapping(value = "/challenges/{id}/scores/{challengerName}")
    public ResponseEntity<?> getChallengerScores(@PathVariable String id, @PathVariable String challengerName) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        if(Strings.isNullOrEmpty(challengerName)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerTotalScoreAtChallenge(challengerName, id));
    }
}