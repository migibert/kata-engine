package com.migibert.challenge.plugins.healthcheck;

import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.ChallengeTestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HealthCheckChallengeTest implements ChallengeTest {

    private RestTemplate template = new RestTemplate();

    @Override
    public String getName() {
        return "HealthcheckBasicTest";
    }

    @Override
    public ChallengeTestResult evaluate(String baseUrl) {
        try {
            ResponseEntity<String> entity = template.getForEntity(baseUrl + "/alive", String.class);
            if (entity.getStatusCode().equals(HttpStatus.OK)) {
                return new ChallengeTestResult(true, "");
            }
            return new ChallengeTestResult(false, "Status code was " + entity.getStatusCode() + ", it should be 200");
        } catch (Exception e) {
            return new ChallengeTestResult(false, "An exception occurred " + e.getMessage());
        }
    }
}
