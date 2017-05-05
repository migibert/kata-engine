package com.migibert.challenge.util;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.migibert.challenge.engine.Score;
import com.migibert.challenge.event.game.*;
import com.migibert.challenge.event.registry.*;
import com.migibert.challenge.event.scoring.ChallengerScoringEndedEvent;
import com.migibert.challenge.event.scoring.ChallengerScoringStartedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    public EventLogger(EventBus bus) {
        bus.register(this);
    }

    @Subscribe
    public void subscribe(GameStartedEvent event) {
        List<String> challengersName = event.getChallengers().stream().map(challenger -> challenger.getName()).collect(Collectors.toList());
        List<String> challengesTitle = event.getChallenges().stream().map(challenge -> challenge.getTitle()).collect(Collectors.toList());
        logger.info("[{}] [{}] Game started with challenges {} and challengers {}", event.getInstant(), event.getGameId(), challengesTitle, challengersName);
    }

    @Subscribe
    public void subscribe(ChallengeStartedEvent event) {
        List<String> challengersName = event.getChallengers().stream().map(challenger -> challenger.getName()).collect(Collectors.toList());
        logger.info("[{}} [{}] Challenge {} started with challengers {}", event.getInstant(), event.getGameId(), event.getChallenge().getTitle(), challengersName);
    }

    @Subscribe
    public void subscribe(ChallengerTestSuiteStartedEvent event) {
        logger.info("[{}] [{}] Challenger {} started challenge {} test suite", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getChallenge().getTitle());
    }

    @Subscribe
    public void subscribe(ChallengerTestStartedEvent event) {
        logger.info("[{}] [{}] Challenger {} started test {}", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getTest().getName());
    }

    @Subscribe
    public void subscribe(ChallengerTestEndedEvent event) {
        if (event.getResult().isSuccess()) {
            logger.info("[{}] [{}] Challenger {} ended test {} with success", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getTest().getName());
        } else {
            logger.info("[{}] [{}] Challenger {} ended test {} without success", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getTest().getName());
        }
    }

    @Subscribe
    public void subscribe(ChallengerTestSuiteEndedEvent event) {
        if (event.getResult().isPassed()) {
            logger.info("[{}] [{}] Challenger {} ended challenge {} test suite with success", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getChallenge().getTitle());
        } else {
            logger.info("[{}] [{}] Challenger {} ended challenge {} test suite without success", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getChallenge().getTitle());
        }
    }

    @Subscribe
    public void subscribe(ChallengeEndedEvent event) {
        logger.info("[{}} [{}] Challenge {} ended", event.getInstant(), event.getGameId(), event.getChallenge().getTitle());
    }

    @Subscribe
    public void subscribe(GameEndedEvent event) {
        logger.info("[{}] [{}] Game ended", event.getInstant(), event.getGameId());
    }

    @Subscribe
    public void subscribe(ChallengerScoringStartedEvent event) {
        logger.info("[{}] [{}] Scoring started for challenger {} at challenge {} with {} successes on {} tests", event.getInstant(), event.getGameId(), event.getChallenger().getName(), event.getChallenge().getTitle(), event.getResult().getSuccesses(), event.getResult().getTestNumber());
    }

    @Subscribe
    public void subscribe(ChallengerScoringEndedEvent event) {
        Score score = event.getScore();
        logger.info("[{}] [{}] Scoring ended for challenger {} at challenge {} with result {}", event.getInstant(), event.getGameId(), score.getChallenger().getName(), score.getChallenge().getTitle(), score.getScore());
    }

    @Subscribe
    public void subscribe(ChallengeActivatedEvent event) {
        logger.info("[{}] Challenge {} has been activated", event.getInstant(), event.getChallenge().getTitle());
    }

    @Subscribe
    public void subscribe(ChallengeDeactivatedEvent event) {
        logger.info("[{}] Challenge {} has been deactivated", event.getInstant(), event.getChallenge().getTitle());
    }

    @Subscribe
    public void subscribe(ChallengeLoadedEvent event) {
        logger.info("[{}] Challenge {} has been loaded", event.getInstant(), event.getChallenge().getTitle());
    }

    @Subscribe
    public void subscribe(ChallengerRegisteredEvent event) {
        logger.info("[{}] Challenger {} has been registered", event.getInstant(), event.getChallenger().getName());
    }

    @Subscribe
    public void subscribe(ChallengerUnregisteredEvent event) {
        logger.info("[{}] Challenger {} has been unregistered", event.getInstant(), event.getChallenger().getName());
    }
}
