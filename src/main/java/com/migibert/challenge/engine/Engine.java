package com.migibert.challenge.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class Engine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Registry registry;

    public List<Score> play() {
        List<Score> result = new ArrayList<Score>();
        for(Challenge challenge : registry.getActiveChallenges()) {
            for (Challenger challenger : registry.getActiveChallengers()) {
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
        return result;
    }
}
