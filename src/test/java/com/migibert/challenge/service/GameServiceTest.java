package com.migibert.challenge.service;

import com.migibert.challenge.engine.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private Engine engine;

    private ScoreScheme scheme = new ScoreScheme(2,1,0);

    @InjectMocks
    private GameService service;

    @Test
    public void create_game() {
        String title = UUID.randomUUID().toString();
        Game game = new Game(title, scheme);
        Game created = service.create(game);
        assertEquals(game.getTitle(), created.getTitle());
        assertEquals(game.getScoreScheme(), created.getScoreScheme());
        assertEquals(game.getId(), created.getId());
        assertTrue(created.getChallengers().isEmpty());
        assertTrue(created.getChallenges().isEmpty());
        assertFalse(created.isActive());
    }

    @Test
    public void get_no_games() {
        List<Game> games = service.getGames();
        assertNotNull(games);
        assertTrue(games.isEmpty());
    }

    @Test
    public void get_all_games() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        List<Game> games = service.getGames();
        assertNotNull(games);
        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        assertEquals(created, games.get(0));
    }

    @Test
    public void get_a_non_existing_gameid() {
        Optional<Game> game = service.get("test");
        assertFalse(game.isPresent());
    }

    @Test
    public void get_an_existing_gameid() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        Optional<Game> game = service.get(created.getId());
        assertTrue(game.isPresent());
        assertEquals(created, game.get());
    }

    @Test
    public void remove_non_existing_game_from_emty_list() {
        service.remove("test");
    }

    @Test
    public void remove_non_existing_game_from_non_empty_list() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.remove("test");
        assertFalse(service.getGames().isEmpty());
        assertEquals(1, service.getGames().size());
        assertEquals(created, service.getGames().get(0));
        assertTrue(service.get(created.getId()).isPresent());
        assertEquals(created, service.get(created.getId()).get());
    }

    @Test
    public void remove_existing_game_from() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.remove(created.getId());
        assertTrue(service.getGames().isEmpty());
        assertFalse(service.get(created.getId()).isPresent());
    }

    @Test
    public void add_challenge_to_non_existing_gameid() {
        Challenge challenge = mock(Challenge.class);
        boolean added = service.addChallengeToGame("test", challenge);
        assertFalse(added);
    }

    @Test
    public void add_challenge_to_existing_gameid() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        Challenge challenge = mock(Challenge.class);
        boolean added = service.addChallengeToGame(created.getId(), challenge);
        assertTrue(added);
        assertFalse(created.getChallenges().isEmpty());
        assertTrue(created.getChallenges().contains(challenge));
    }

    @Test
    public void add_challenger_to_non_existing_gameid() {
        Challenger challenger = mock(Challenger.class);
        boolean added = service.addChallengerToGame("test", challenger);
        assertFalse(added);
    }

    @Test
    public void add_challenger_to_existing_gameid() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        Challenger challenger = mock(Challenger.class);
        boolean added = service.addChallengerToGame(created.getId(), challenger);
        assertTrue(added);
        assertFalse(created.getChallengers().isEmpty());
        assertTrue(created.getChallengers().contains(challenger));
    }

    @Test
    public void get_non_existing_challenger_games() {
        List<Game> games = service.getChallengerGames("test");
        assertTrue(games.isEmpty());
    }

    @Test
    public void given_one_game_exists_get_existing_challenger_games() {
        Challenger challenger = new Challenger("challenger", "http://localhost:8080/api");
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.addChallengerToGame(created.getId(), challenger);
        List<Game> games = service.getChallengerGames(challenger.getName());
        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        assertEquals(created, games.get(0));
    }

    @Test
    public void given_many_game_exist_get_existing_challenger_games() {
        Challenger challenger = new Challenger("challenger", "http://localhost:8080/api");
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.addChallengerToGame(created.getId(), challenger);
        List<Game> games = service.getChallengerGames(challenger.getName());
        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        assertEquals(created, games.get(0));
    }

    @Test
    public void get_non_existing_challenge_games() {
        List<Game> games = service.getChallengeGames("test");
        assertTrue(games.isEmpty());
    }

    @Test
    public void given_one_game_exists_get_existing_challenge_games() {
        String challengeId = UUID.randomUUID().toString();
        Challenge challenge = mock(Challenge.class);
        when(challenge.getId()).thenReturn(challengeId);
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.addChallengeToGame(created.getId(), challenge);
        List<Game> games = service.getChallengeGames(challenge.getId());
        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        assertEquals(created, games.get(0));
    }

    @Test
    public void given_many_game_exist_get_existing_challenge_games() {
        String challengeId = UUID.randomUUID().toString();
        Challenge challenge = mock(Challenge.class);
        when(challenge.getId()).thenReturn(challengeId);
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.addChallengeToGame(created.getId(), challenge);
        List<Game> games = service.getChallengeGames(challenge.getId());
        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        assertEquals(created, games.get(0));
    }

    @Test
    public void activate_non_existing_game() {
        boolean activated = service.activate("test");
        assertFalse(activated);
    }

    @Test
    public void activate_existing_game() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        boolean activated = service.activate(created.getId());
        assertTrue(activated);
        assertTrue(service.get(created.getId()).get().isActive());
    }

    @Test
    public void activate_existing_game_should_not_activate_other_games() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        boolean activated = service.activate(created.getId());
        assertTrue(activated);
        Stream<Game> games = service.getGames().stream().filter(game -> game.isActive());
        Optional<Game> res = games.findFirst();
        assertTrue(res.isPresent());
        assertEquals(created, res.get());
    }

    @Test
    public void deactivate_non_existing_game() {
        boolean deactivated = service.activate("test");
        assertFalse(deactivated);
    }

    @Test
    public void deactivate_existing_game() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.activate(created.getId());
        boolean deactivated = service.deactivate(created.getId());
        assertTrue(deactivated);
        assertFalse(service.get(created.getId()).get().isActive());
    }

    @Test
    public void deactivate_existing_game_should_not_activate_other_games() {
        Game created = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.getGames().stream().forEach(game -> service.activate(game.getId()));

        boolean deactivated = service.deactivate(created.getId());
        assertTrue(deactivated);
        Stream<Game> games = service.getGames().stream().filter(game -> !game.isActive());
        Optional<Game> res = games.findFirst();
        assertTrue(res.isPresent());
        assertEquals(created, res.get());
    }

    @Test
    public void play() {
        doNothing().when(engine).play(any());
        Game game1 = service.create(new Game(UUID.randomUUID().toString(), scheme));
        Game game2 = service.create(new Game(UUID.randomUUID().toString(), scheme));
        Game game3 = service.create(new Game(UUID.randomUUID().toString(), scheme));
        service.activate(game2.getId());
        service.activate(game3.getId());

        service.play();

        ArgumentCaptor<Game> eventCaptor = ArgumentCaptor.forClass(Game.class);
        verify(engine, times(2)).play(eventCaptor.capture());
        verify(engine, never()).play(game1);
        verify(engine, times(1)).play(game2);
        verify(engine, times(1)).play(game3);
        List<Game> values = eventCaptor.getAllValues();
        assertFalse(values.contains(game1));
        assertTrue(values.contains(game2));
        assertTrue(values.contains(game3));
    }
}
