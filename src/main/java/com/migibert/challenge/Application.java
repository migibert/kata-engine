package com.migibert.challenge;

import com.google.common.eventbus.EventBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import ro.fortsoft.pf4j.DefaultPluginManager;
import ro.fortsoft.pf4j.PluginManager;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    public PluginManager pluginManager() {
        return new DefaultPluginManager();
    }
}
