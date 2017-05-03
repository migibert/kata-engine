package com.migibert.challenge.engine;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.event.game.*;
import com.migibert.challenge.event.scoring.ChallengerScoringEndedEvent;
import com.migibert.challenge.event.scoring.ChallengerScoringStartedEvent;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ChallengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Engine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EventBus bus;

    @Inject
    private ChallengeService challengeService;

    @Inject
    private ChallengerService challengerService;

    @Scheduled(fixedDelayString = "${game.loop.delay}")
    public void play() {
        game();
    }

    public void game() {
        List<Challenge> activeChallenges = challengeService.getActiveChallenges();
        List<Challenger> activeChallengers = challengerService.getActiveChallengers();
        String gameId = UUID.randomUUID().toString();
        bus.post(new GameStartedEvent(gameId, activeChallengers, activeChallenges));

        for(Challenge challenge : activeChallenges) {
            bus.post(new ChallengeStartedEvent(gameId, challenge, activeChallengers));
            for (Challenger challenger : activeChallengers) {
                ChallengeTestSuiteResult result = evaluate(gameId, challenger, challenge);
                score(gameId, challenger, challenge, result);
            }
            bus.post(new ChallengeEndedEvent(gameId, challenge));

        }
        bus.post(new GameEndedEvent(gameId));
    }

    public ChallengeTestSuiteResult evaluate(String gameId, Challenger challenger, Challenge challenge) {
        bus.post(new ChallengerTestSuiteStartedEvent(gameId, challenge, challenger));
        String url = challenger.getBaseUrl() + challenge.getContract().getPath();
        List<ChallengeTestResult> results = new ArrayList<>();
        ChallengeTestSuite testSuite = challenge.getTestSuite();
        for(ChallengeTest test : testSuite.getTests()) {
            bus.post(new ChallengerTestStartedEvent(gameId, test, challenger));
            ChallengeTestResult result = test.evaluate(url);
            results.add(result);
            bus.post(new ChallengerTestEndedEvent(gameId, test, challenger, result));
        }
        ChallengeTestSuiteResult testSuiteResult = new ChallengeTestSuiteResult(results, testSuite);
        bus.post(new ChallengerTestSuiteEndedEvent(gameId, challenge, challenger, testSuiteResult));
        return testSuiteResult;
    }

    public int score(String gameId, Challenger challenger, Challenge challenge, ChallengeTestSuiteResult result) {
        bus.post(new ChallengerScoringStartedEvent(gameId, challenger, challenge, result));
        int score = 0;
        if(result.getSuccesses() == 0) {
            score = challenge.getFailureScore();
        } else if(result.getFailures() == 0) {
            score = challenge.getSuccessScore();
        } else {
            score = challenge.getPartialSuccessScore();
        }
        bus.post(new ChallengerScoringEndedEvent(gameId, new Score(score, challenge, challenger)));
        return score;
    }
}
