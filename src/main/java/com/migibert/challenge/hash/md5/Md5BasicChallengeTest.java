package com.migibert.challenge.hash.md5;

import com.migibert.challenge.engine.ChallengeTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Md5BasicChallengeTest implements ChallengeTest {
    private RestTemplate template = new RestTemplate();

    @Override
    public boolean evaluate(String url) {
        String inputData = "Thisisawonderfultest";
        Map<String, String> pathParameterValues = new HashMap<String, String>();
        pathParameterValues.put("value_to_hash", inputData);
        ResponseEntity<String> response = template.getForEntity(url, String.class, pathParameterValues);

        if(!response.getStatusCode().is2xxSuccessful()) {
            return false;
        }
        if(response.getStatusCode().value() != 200) {
            return false;
        }
        if(!response.getBody().equals("928a0d249855c6a93f693a074cb1a8c5")) {
            return false;
        }
        return true;
    }
}
