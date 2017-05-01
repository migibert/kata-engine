package com.migibert.challenge.util;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.migibert.challenge.engine.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class EventLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    public EventLogger(EventBus bus) {
        bus.register(this);
    }

    @Subscribe
    public void subscribe(ChallengeStartedEvent event) {
        logger.info("Challenge {} ended at {}", event.getChallenge().getTitle(), event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengeEndedEvent event) {
        logger.info("Challenge {} ended at {}", event.getChallenge().getTitle(), event.getDate());
    }

    @Subscribe
    public void subscribe(GameStartedEvent event) {
        logger.info("Game started at {}", event.getDate());
    }

    @Subscribe
    public void subscribe(GameEndedEvent event) {
        logger.info("Game ended at {}", event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengerRegisteredEvent event) {
        logger.info("Challenger {} registered at {}", event.getChallenger().getName(), event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengerUnregisteredEvent event) {
        logger.info("Challenger {} unregistered at {}", event.getChallenger().getName(), event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengerEvaluationStartedEvent event) {
        logger.info("Challenger {} evaluation for challenge {} with {} sucesses and {} failures started at {}", event.getChallenger().getName(), event.getChallenge().getTitle(), event.getSuccesses(), event.getFailures(), event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengerEvaluationEndedEvent event) {
        logger.info("Challenger {} evaluation for challenge {} with score {} ended at {}", event.getChallenger().getName(), event.getChallenge().getTitle(), event.getScore(), event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengerTestFailedEvent event) {
        logger.info("Challenger {} failed test {} for challenge {} at {}", event.getChallenger().getName(), event.getTest().getClass().getSimpleName(), event.getChallenge().getTitle(), event.getDate());
    }

    @Subscribe
    public void subscribe(ChallengerTestSuccededEvent event) {
        logger.info("Challenger {} succeded test {} for challenge {} at {}", event.getChallenger().getName(), event.getTest().getClass().getSimpleName(), event.getChallenge().getTitle(), event.getDate());
    }
}
