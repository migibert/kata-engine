package com.migibert.challenge.service;

import com.migibert.challenge.engine.Challenge;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ro.fortsoft.pf4j.PluginManager;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChallengeServiceTest {

    private PluginManager manager = mock(PluginManager.class);
    private ChallengeService service;

    @Test
    public void get_loaded_challenges_at_initialization() {
        Challenge challenge1 = mock(Challenge.class);
        Challenge challenge2 = mock(Challenge.class);
        List<Challenge> challenges = Lists.newArrayList(challenge1, challenge2);
        when(manager.getExtensions(Challenge.class)).thenReturn(challenges);

        service = new ChallengeService(manager);
        List<Challenge> challengeList = service.getChallenges();

        verify(manager, times(1)).getExtensions(Challenge.class);
        assertEquals(2, challengeList.size());
        assertTrue(challengeList.contains(challenge1));
        assertTrue(challengeList.contains(challenge2));
    }

    @Test
    public void get_non_existing_challenge() {
        Challenge challenge1 = mock(Challenge.class);
        Challenge challenge2 = mock(Challenge.class);
        List<Challenge> challenges = Lists.newArrayList(challenge1, challenge2);
        when(manager.getExtensions(Challenge.class)).thenReturn(challenges);
        when(challenge1.getId()).thenReturn("1");
        when(challenge2.getId()).thenReturn("2");

        service = new ChallengeService(manager);
        Optional<Challenge> challenge = service.getChallenge("who knows ?");

        assertFalse(challenge.isPresent());
    }

    @Test
    public void get_existing_challenge() {
        Challenge challenge1 = mock(Challenge.class);
        Challenge challenge2 = mock(Challenge.class);
        List<Challenge> challenges = Lists.newArrayList(challenge1, challenge2);
        when(manager.getExtensions(Challenge.class)).thenReturn(challenges);
        when(challenge1.getId()).thenReturn("1");
        when(challenge2.getId()).thenReturn("2");

        service = new ChallengeService(manager);
        Optional<Challenge> res1 = service.getChallenge(challenge1.getId());
        Optional<Challenge> res2 = service.getChallenge(challenge2.getId());

        assertTrue(res1.isPresent());
        assertEquals(challenge1, res1.get());
        assertTrue(res2.isPresent());
        assertEquals(challenge2, res2.get());
    }
}
