package com.migibert.challenge.event;

import com.migibert.challenge.engine.Engine;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EventsOrderTest {

    @Test
    public void testEventsOrdering() throws InterruptedException {
        EngineEvent a = new EngineEvent();
        Thread.sleep(10);
        EngineEvent b = new EngineEvent();
        EngineEvent c = new EngineEvent();
        assertTrue(a.compareTo(b) < 0);
        assertTrue(a.compareTo(c) < 0);
        assertTrue(b.compareTo(b) == 0);
    }
}
