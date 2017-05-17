package com.migibert.challenge.engine;

import java.util.Objects;

public class Challenger {

    private String name;
    private String baseUrl;

    public Challenger() {
    }

    public Challenger(String name, String baseUrl) {
        this.name = name;
        this.baseUrl = baseUrl;
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
    public int hashCode() {
        return Objects.hash(name, baseUrl);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Challenger other = (Challenger) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.baseUrl, other.baseUrl);
    }
}
