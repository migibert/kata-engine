package com.migibert.challenge.controller;

import com.migibert.challenge.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;

public class GameController {

    @Inject
    private ScoreService scoreService;

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
        if (StringUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scoreService.getChallengerScoreAtGame(challengerName, id));
    }

}
