package com.migibert.challenge.plugins.md5;

import com.migibert.challenge.engine.ChallengeContract;
import org.springframework.http.HttpMethod;


public class Md5ChallengeContract implements ChallengeContract {

    @Override
    public String getPath() {
        return "/md5/{value_to_hash}";
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getDescription() {
        return "You have to calculate MD5 hash that you will received at " + getPath() + " using " + getMethod() + " HTTP verb";
    }
}
