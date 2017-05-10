package com.migibert.challenge.controller;

import com.migibert.challenge.engine.Challenge;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Controller
public class ChallengeController {

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
        if (StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Challenge> result = service.getChallenge(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result.get());
    }
}