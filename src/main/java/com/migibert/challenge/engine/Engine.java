package com.migibert.challenge.engine;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.engine.event.*;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ChallengerService;
import com.migibert.challenge.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class Engine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EventBus bus;

    @Inject
    private ChallengeService challengeService;

    @Inject
    private ChallengerService challengerService;

    @Inject
    private ScoreService scoreService;

    @Scheduled(fixedDelayString = "${game.loop.delay}")
    public void play() {
        game();
    }

    public void game() {
        bus.post(new GameStartedEvent());
        Collection<Score> result = new ArrayList<>();
        for(Challenge challenge : challengeService.getActiveChallenges()) {
            bus.post(new ChallengeStartedEvent(challenge));
            for (Challenger challenger : challengerService.getActiveChallengers()) {
                String url = challenger.getBaseUrl() + challenge.getContract().getPath();
                int success = 0;
                int failure = 0;
                for(ChallengeTest test : challenge.getTests()) {
                    if(test.evaluate(url)) {
                        bus.post(new ChallengerTestSuccededEvent(challenger, challenge, test));
                        success++;
                    } else {
                        bus.post(new ChallengerTestFailedEvent(challenger, challenge, test));
                        failure++;
                    }
                }
                bus.post(new ChallengerEvaluationStartedEvent(challenger, challenge, success, failure));
                int score = 0;
                if(success == 0) {
                    score = challenge.getFailureScore();
                } else if(failure == 0) {
                    score = challenge.getSuccessScore();
                } else {
                    score = challenge.getPartialSuccessScore();
                }
                bus.post(new ChallengerEvaluationEndedEvent(challenger, challenge, score));
                result.add(new Score(score, challenge, challenger));
            }
        }
        logger.info("Recording results {}", result);
        scoreService.register(result);
        bus.post(new GameEndedEvent());
    }
}
