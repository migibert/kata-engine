package com.migibert.challenge.event;

import com.migibert.challenge.engine.Engine;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EventsOrderTest {

    @Test
    public void when_events_are_created_at_1_ms_interval_then_first_created_compares_inferior() throws InterruptedException {
        EngineEvent a = new EngineEvent();
        Thread.sleep(1);
        EngineEvent b = new EngineEvent();
        EngineEvent c = new EngineEvent();
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void when_events_are_created_at_the_same_instant_then_they_compare_equals() throws InterruptedException {
        EngineEvent a = new EngineEvent();
        EngineEvent b = new EngineEvent();
        EngineEvent c = new EngineEvent();
        assertTrue(a.compareTo(b) == 0);
    }
}
