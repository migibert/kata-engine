package com.migibert.challenge.engine;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.event.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class Engine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EventBus bus;

    public void play(Game game) {
        game(game.getId(), game.getChallenges(), game.getChallengers());
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
        List<ChallengeTestResult> results = new ArrayList<>();
        ChallengeTestSuite testSuite = challenge.getTestSuite();
        for (ChallengeTest test : testSuite.getTests()) {
            bus.post(new ChallengerTestStartedEvent(gameId, test, challenger));
            ChallengeTestResult result = test.evaluate(challenger.getBaseUrl());
            results.add(result);
            bus.post(new ChallengerTestEndedEvent(gameId, test, challenger, result));
        }
        ChallengeTestSuiteResult testSuiteResult = new ChallengeTestSuiteResult(results, testSuite);
        bus.post(new ChallengerTestSuiteEndedEvent(gameId, challenge, challenger, testSuiteResult));
        return testSuiteResult;
    }
}
