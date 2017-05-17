package com.migibert.challenge.plugins.md5;

import com.migibert.challenge.engine.ChallengeTest;
import com.migibert.challenge.engine.ChallengeTestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Md5BasicChallengeTest implements ChallengeTest {
    private RestTemplate template = new RestTemplate();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getName() {
        return "MD5 basic test";
    }

    @Override
    public ChallengeTestResult evaluate(String baseUrl) {
        String inputData = "Thisisawonderfultest";
        Map<String, String> pathParameterValues = new HashMap<String, String>();
        pathParameterValues.put("value_to_hash", inputData);

        boolean result = false;
        String reason = "";
        try {
            String url = baseUrl + "/md5";
            ResponseEntity<String> response = template.getForEntity(url, String.class, pathParameterValues);
            if (!response.getStatusCode().is2xxSuccessful()) {
                return new ChallengeTestResult(false, "Status code is not 2xx");
            }
            if (response.getStatusCode().value() != 200) {
                return new ChallengeTestResult(false, "Status code is not 200");
            }
            if (!response.getBody().equals("928a0d249855c6a93f693a074cb1a8c5")) {
                return new ChallengeTestResult(false, "Thisisawonderfultest MD5 hash should be 928a0d249855c6a93f693a074cb1a8c5 but was " + response.getBody());
            }
            return new ChallengeTestResult(true, "");
        } catch (Exception e) {
            return new ChallengeTestResult(false, "An exception occurred " + e.getMessage());
        }
    }
}
