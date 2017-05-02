package com.migibert.challenge.plugins.healthcheck;


import com.migibert.challenge.engine.ChallengeContract;
import org.springframework.http.HttpMethod;

public class HealthCheckChallengeContract implements ChallengeContract {

    @Override
    public String getPath() {
        return "/alive";
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getDescription() {
        return "Test challenger health sending a GET request to /alive endpoint";
    }
}
