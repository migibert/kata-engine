package com.migibert.challenge.controller;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.engine.Engine;
import com.migibert.challenge.engine.Registry;
import com.migibert.challenge.engine.Score;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ChallengeController {

    @Inject
    private Engine engine;

    @Inject
    private Registry registry;

    @GetMapping(value = "/challenges")
    public ResponseEntity<?> getChallenges() {
        List<Challenge> result = registry.getChallenges();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/challenges/{id}")
    public ResponseEntity<?> getChallenge(@PathVariable String id) {
        Challenge result = registry.getChallenge(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/challenges/{id}")
    public ResponseEntity<?> deleteChallenge(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/challenges/{id}/start")
    public ResponseEntity<?> startChallenge(@PathVariable String id) {
        List<Score> results = engine.play();
        return ResponseEntity.ok(results);
    }
}