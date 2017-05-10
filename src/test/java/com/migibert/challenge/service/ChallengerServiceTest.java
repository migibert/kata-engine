package com.migibert.challenge.service;

import com.google.common.eventbus.EventBus;
import com.migibert.challenge.engine.Challenger;
import com.migibert.challenge.event.registry.ChallengerRegisteredEvent;
import com.migibert.challenge.event.registry.ChallengerUnregisteredEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChallengerServiceTest {
    @Mock
    private EventBus bus;

    @InjectMocks
    private ChallengerService service;

    @Test
    public void register_non_existing_challenger() {
        Challenger challenger = new Challenger("challenger", "http://localhost:8080/api");
        service.registerChallenger(challenger);

        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
        verify(bus, times(1)).post(eventCaptor.capture());

        List<Object> values = eventCaptor.getAllValues();
        assertEquals(1, values.size());
        assertEquals(ChallengerRegisteredEvent.class, values.get(0).getClass());

        ChallengerRegisteredEvent event = (ChallengerRegisteredEvent)values.get(0);
        assertEquals(challenger, event.getChallenger());
    }

    @Test
    public void register_existing_challenger() {
        Challenger challenger = new Challenger("challenger", "http://localhost:8080/api");
        service.registerChallenger(challenger);
        boolean result = service.registerChallenger(challenger);
        assertFalse(result);

        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
        verify(bus, times(1)).post(eventCaptor.capture());

        List<Object> values = eventCaptor.getAllValues();
        assertEquals(1, values.size());
        assertEquals(ChallengerRegisteredEvent.class, values.get(0).getClass());

        ChallengerRegisteredEvent event = (ChallengerRegisteredEvent)values.get(0);
        assertEquals(challenger, event.getChallenger());
    }


    @Test
    public void register_challenger_with_unavailable_name() {
        Challenger challenger1 = new Challenger("challenger", "http://localhost:8080/api");
        Challenger challenger2 = new Challenger("challenger", "http://localhost:8180/api");
        service.registerChallenger(challenger1);
        boolean result = service.registerChallenger(challenger2);
        assertFalse(result);

        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
        verify(bus, times(1)).post(eventCaptor.capture());

        List<Object> values = eventCaptor.getAllValues();
        assertEquals(1, values.size());
        assertEquals(ChallengerRegisteredEvent.class, values.get(0).getClass());

        ChallengerRegisteredEvent event = (ChallengerRegisteredEvent)values.get(0);
        assertEquals(challenger1, event.getChallenger());
    }

    @Test
    public void register_challenger_with_unavailable_url() {
        Challenger challenger1 = new Challenger("challenger1", "http://localhost:8080/api");
        Challenger challenger2 = new Challenger("challenger2", "http://localhost:8080/api");
        service.registerChallenger(challenger1);
        boolean result = service.registerChallenger(challenger2);
        assertFalse(result);

        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
        verify(bus, times(1)).post(eventCaptor.capture());

        List<Object> values = eventCaptor.getAllValues();
        assertEquals(1, values.size());
        assertEquals(ChallengerRegisteredEvent.class, values.get(0).getClass());

        ChallengerRegisteredEvent event = (ChallengerRegisteredEvent)values.get(0);
        assertEquals(challenger1, event.getChallenger());
    }

    @Test
    public void unregister_non_existing_challenger() {
        boolean removed = service.unregisterChallenger("challenger");
        assertFalse(removed);
        verify(bus, never()).post(any());
    }

    @Test
    public void unregister_existing_challenger() {
        Challenger challenger = new Challenger(UUID.randomUUID().toString(), "http://localhost:8080/api");
        service.registerChallenger(challenger);
        boolean removed = service.unregisterChallenger(challenger.getName());
        assertTrue(removed);

        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
        verify(bus, times(2)).post(eventCaptor.capture());
        List<Object> values = eventCaptor.getAllValues();
        assertEquals(2, values.size());
        assertEquals(ChallengerUnregisteredEvent.class, values.get(1).getClass());
        ChallengerUnregisteredEvent event = (ChallengerUnregisteredEvent) values.get(1);
        assertEquals(challenger.getName(), event.getChallengerName());
    }

    @Test
    public void get_all_challengers_when_no_challenger_is_registered() {
        List<Challenger> challengers = service.listChallengers();
        assertNotNull(challengers);
        assertEquals(0, challengers.size());
    }

    @Test
    public void get_all_challengers_wen_challengers_are_registered() {
        Challenger challenger1 = new Challenger("challenger1", "http://localhost:8080/api");
        Challenger challenger2 = new Challenger("challenger2", "http://localhost:8081/api");
        Challenger challenger3 = new Challenger("challenger3", "http://localhost:8082/api");

        service.registerChallenger(challenger1);
        service.registerChallenger(challenger2);
        service.registerChallenger(challenger3);

        List<Challenger> challengers = service.listChallengers();
        assertNotNull(challengers);
        assertEquals(3, challengers.size());
        assertTrue(challengers.contains(challenger1));
        assertTrue(challengers.contains(challenger2));
        assertTrue(challengers.contains(challenger3));
    }

    @Test
    public void get_all_challengers_wen_challengers_are_unregistered() {
        Challenger challenger1 = new Challenger("challenger1", "http://localhost:8080/api");
        Challenger challenger2 = new Challenger("challenger2", "http://localhost:8081/api");
        Challenger challenger3 = new Challenger("challenger3", "http://localhost:8082/api");

        service.registerChallenger(challenger1);
        service.registerChallenger(challenger2);
        service.registerChallenger(challenger3);
        service.unregisterChallenger(challenger2.getName());

        List<Challenger> challengers = service.listChallengers();
        assertNotNull(challengers);
        assertEquals(2, challengers.size());
        assertTrue(challengers.contains(challenger1));
        assertTrue(challengers.contains(challenger3));
    }

    @Test
    public void get_non_registered_challenger() {
        Optional<Challenger> challenger = service.getChallenger("test");
        assertFalse(challenger.isPresent());
    }

    @Test
    public void get_registered_challenger() {
        Challenger challenger = new Challenger("challenger", "http://localhost:8080/api");
        service.registerChallenger(challenger);

        Optional<Challenger> found = service.getChallenger(challenger.getName());
        assertTrue(found.isPresent());
        assertEquals(challenger, found.get());
    }

    @Test
    public void get_unregistered_challenger() {
        Challenger challenger = new Challenger("challenger", "http://localhost:8080/api");
        service.registerChallenger(challenger);
        service.unregisterChallenger(challenger.getName());
        Optional<Challenger> found = service.getChallenger(challenger.getName());
        assertFalse(found.isPresent());
    }
}
