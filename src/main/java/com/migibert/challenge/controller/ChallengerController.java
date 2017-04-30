package com.migibert.challenge.controller;

import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.engine.Engine;
import com.migibert.challenge.engine.Registry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ChallengerController {

    @Inject
    private Engine engine;

    @Inject
    private Registry registry;

    @PostMapping(value = "/challengers")
    public ResponseEntity<?> createChallenger(@RequestBody Challenger challenger) {
        registry.registerChallenger(challenger);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/challengers")
    public ResponseEntity<?> getChallengers() {
        List<Challenger> result = registry.listChallengers();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/challengers/{name}")
    public ResponseEntity<?> getChallenger(@PathVariable String name) {
        Challenger result = registry.getChallenger(name);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/challengers/{name}")
    public ResponseEntity<?> deleteChallenger(@PathVariable String name) {
        registry.deleteChallenger(name);
        return ResponseEntity.noContent().build();
    }
}