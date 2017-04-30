package com.migibert.challenge.engine;

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
import java.util.List;

@Component
public class Engine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ChallengeService challengeService;

    @Inject
    private ChallengerService challengerService;

    @Inject
    private ScoreService scoreService;

    @Scheduled(fixedDelay = 60000)
    public void play() {
        logger.info("Let's play!!!");
        Collection<Score> result = new ArrayList<>();
        for(Challenge challenge : challengeService.getActiveChallenges()) {
            logger.info("Looking for challengers for challenge {}", challenge.getTitle());
            for (Challenger challenger : challengerService.getActiveChallengers()) {
                logger.info("Challenger {} turn, executing test suite", challenger.getName());
                String url = challenger.getBaseUrl() + challenge.getContract().getPath();
                int success = 0;
                int failure = 0;
                for(ChallengeTest test : challenge.getTests()) {
                    logger.info("Challenger {} tries to validate test {} for challenge {}", challenger.getName(), test.getClass().getSimpleName(), challenge.getTitle());
                    if(test.evaluate(url)) {
                        logger.info("Challenger {} validated test {} for challenge {}", challenger.getName(), test.getClass().getSimpleName(), challenge.getTitle());
                        success++;
                    } else {
                        logger.info("Challenger {} failed test {} for challenge {}", challenger.getName(), test.getClass().getSimpleName(), challenge.getTitle());
                        failure++;
                    }
                }
                logger.info("Evaluating challenger {} results at challenge {}", challenger.getName(), challenge.getTitle());
                if(success == 0) {
                    result.add(new Score(challenge.getFailureScore(), challenge, challenger));
                } else if(failure == 0) {
                    result.add(new Score(challenge.getSuccessScore(), challenge, challenger));
                } else {
                    result.add(new Score(challenge.getPartialSuccessScore(), challenge, challenger));
                }
            }
        }
        logger.info("Recording results {}", result);
        scoreService.register(result);
    }
}
