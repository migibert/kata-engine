package com.migibert.challenge.engine;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.migibert.challenge.event.game.*;
import com.migibert.challenge.event.scoring.ChallengerScoringEndedEvent;
import com.migibert.challenge.event.scoring.ChallengerScoringStartedEvent;
import com.migibert.challenge.service.ChallengeService;
import com.migibert.challenge.service.ChallengerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EngineTest {
    @Mock
    private EventBus bus;

    @Mock
    private ChallengeService challengeService;

    @Mock
    private ChallengerService challengerService;

    @InjectMocks
    private Engine engine;

    @Test
    public void testGameNotifications() {
        String gameId = "abc";
        Challenger challenger = mock(Challenger.class);
        Challenge challenge = mock(Challenge.class);

        List<Challenger> challengers = Lists.newArrayList(challenger);
        List<Challenge> challenges = Lists.newArrayList(challenge);

        ChallengeContract contract = mock(ChallengeContract.class);
        ChallengeTestSuite suite = mock(ChallengeTestSuite.class);
        ChallengeTest test = mock(ChallengeTest.class);
        ChallengeTestResult result = new ChallengeTestResult(true, "This is a test");
        ChallengeTestSuiteResult suiteResult = new ChallengeTestSuiteResult(Lists.newArrayList(result), suite);

        given(challenger.getBaseUrl()).willReturn("http://localhost:8080/api");
        given(test.evaluate(anyString())).willReturn(result);
        given(suite.getTests()).willReturn(Lists.newArrayList(test));
        given(challenge.getTestSuite()).willReturn(suite);
        given(challenge.getContract()).willReturn(contract);

        engine.game(gameId, challenges, challengers);

        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
        verify(bus, times(10)).post(eventCaptor.capture());

        List<Object> calls = eventCaptor.getAllValues();
        GameStartedEvent gameStartedEvent = (GameStartedEvent)calls.get(0);
        assertEquals(challenges, gameStartedEvent.getChallenges());
        assertEquals(challengers, gameStartedEvent.getChallengers());
        assertEquals(gameId, gameStartedEvent.getGameId());

        ChallengeStartedEvent challengeStartedEvent = (ChallengeStartedEvent) calls.get(1);
        assertEquals(challenge, challengeStartedEvent.getChallenge());
        assertEquals(challengers, challengeStartedEvent.getChallengers());
        assertEquals(gameId, challengeStartedEvent.getGameId());

        ChallengerTestSuiteStartedEvent challengerTestSuiteStartedEvent = (ChallengerTestSuiteStartedEvent) calls.get(2);
        assertEquals(challenger, challengerTestSuiteStartedEvent.getChallenger());
        assertEquals(challenge, challengerTestSuiteStartedEvent.getChallenge());
        assertEquals(gameId, challengerTestSuiteStartedEvent.getGameId());

        ChallengerTestStartedEvent challengerTestStartedEvent = (ChallengerTestStartedEvent) calls.get(3);
        assertEquals(challenger, challengerTestStartedEvent.getChallenger());
        assertEquals(test, challengerTestStartedEvent.getTest());
        assertEquals(gameId, challengerTestStartedEvent.getGameId());

        ChallengerTestEndedEvent challengerTestEndedEvent = (ChallengerTestEndedEvent) calls.get(4);
        assertEquals(challenger, challengerTestEndedEvent.getChallenger());
        assertEquals(test, challengerTestEndedEvent.getTest());
        assertEquals(result, challengerTestEndedEvent.getResult());
        assertEquals(gameId, challengerTestEndedEvent.getGameId());

        ChallengerTestSuiteEndedEvent challengerTestSuiteEndedEvent = (ChallengerTestSuiteEndedEvent) calls.get(5);
        assertEquals(challenger, challengerTestSuiteEndedEvent.getChallenger());
        assertEquals(challenge, challengerTestSuiteEndedEvent.getChallenge());
        assertEquals(suiteResult, challengerTestSuiteEndedEvent.getResult());
        assertEquals(gameId, challengerTestSuiteEndedEvent.getGameId());

        ChallengerScoringStartedEvent challengerScoringStartedEvent = (ChallengerScoringStartedEvent) calls.get(6);
        assertEquals(challenger, challengerScoringStartedEvent.getChallenger());
        assertEquals(challenge, challengerScoringStartedEvent.getChallenge());
        assertEquals(suiteResult, challengerScoringStartedEvent.getResult());

        ChallengerScoringEndedEvent challengerScoringEndedEvent = (ChallengerScoringEndedEvent) calls.get(7);
        assertEquals(challenge, challengerScoringEndedEvent.getScore().getChallenge());
        assertEquals(challenger, challengerScoringEndedEvent.getScore().getChallenger());
        assertEquals(0, challengerScoringEndedEvent.getScore().getScore());

        ChallengeEndedEvent challengeEndedEvent = (ChallengeEndedEvent) calls.get(8);
        assertEquals(challenge, challengeEndedEvent.getChallenge());
        assertEquals(gameId, challengeEndedEvent.getGameId());

        GameEndedEvent gameEndedEvent = (GameEndedEvent) calls.get(9);
        assertEquals(gameId, gameEndedEvent.getGameId());
    }
}
