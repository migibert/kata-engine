package com.migibert.challenge.engine;

public class Challenger implements Activable {

    private String name;
    private String baseUrl;
    private boolean active;

    public Challenger() {
    }

    public Challenger(String name, String baseUrl, boolean active) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.active = active;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
