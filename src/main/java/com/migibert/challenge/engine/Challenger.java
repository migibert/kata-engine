package com.migibert.challenge.engine;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(name, baseUrl, active);
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
                && Objects.equals(this.baseUrl, other.baseUrl)
                && Objects.equals(this.active, other.active);
    }
}
