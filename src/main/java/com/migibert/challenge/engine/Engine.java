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
        List<Score> result = new ArrayList<Score>();
        for(Challenge challenge : challengeService.getActiveChallenges()) {
            for (Challenger challenger : challengerService.getActiveChallengers()) {
                String url = challenger.getBaseUrl() + challenge.getContract().getPath();
                int success = 0;
                int failure = 0;
                for(ChallengeTest test : challenge.getTests()) {
                    if(test.evaluate(url)) {
                        success++;
                    } else {
                        failure++;
                    }
                }
                if(success == 0) {
                    result.add(new Score(challenge.getFailureScore(), challenge, challenger));
                } else if(failure == 0) {
                    result.add(new Score(challenge.getSuccessScore(), challenge, challenger));
                } else {
                    result.add(new Score(challenge.getPartialSuccessScore(), challenge, challenger));
                }
            }
        }
        scoreService.register(result);
    }
}
