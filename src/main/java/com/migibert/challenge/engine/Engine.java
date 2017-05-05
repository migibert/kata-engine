package com.migibert.challenge.engine;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.event.game.*;
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
        String gameId = UUID.randomUUID().toString();
        List<Challenge> activeChallenges = challengeService.getActiveChallenges();
        List<Challenger> activeChallengers = challengerService.getActiveChallengers();
        game(gameId, activeChallenges, activeChallengers);
    }

    public void game(String gameId, List<Challenge> activeChallenges, List<Challenger> activeChallengers) {
        bus.post(new GameStartedEvent(gameId, activeChallengers, activeChallenges));

        for (Challenge challenge : activeChallenges) {
            bus.post(new ChallengeStartedEvent(gameId, challenge, activeChallengers));
            for (Challenger challenger : activeChallengers) {
                evaluate(gameId, challenger, challenge);
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
        for (ChallengeTest test : testSuite.getTests()) {
            bus.post(new ChallengerTestStartedEvent(gameId, test, challenger));
            ChallengeTestResult result = test.evaluate(url);
            results.add(result);
            bus.post(new ChallengerTestEndedEvent(gameId, test, challenger, result));
        }
        ChallengeTestSuiteResult testSuiteResult = new ChallengeTestSuiteResult(results, testSuite);
        bus.post(new ChallengerTestSuiteEndedEvent(gameId, challenge, challenger, testSuiteResult));
        return testSuiteResult;
    }
}
