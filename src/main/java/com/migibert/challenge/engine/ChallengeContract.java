package com.migibert.challenge.engine;


import org.springframework.http.HttpMethod;

public interface ChallengeContract {
    String getPath();
    HttpMethod getMethod();
    String getDescription();
}
