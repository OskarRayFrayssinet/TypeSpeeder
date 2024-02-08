package se.ju23.typespeeder.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.ju23.typespeeder.data.model.Player;
import se.ju23.typespeeder.data.repo.PlayerRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerServiceTest{
    @Mock
    private PlayerRepo playerRepo;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePlayer(){
        Player player = new Player();
        player.setLogName("testuser");
        player.setPassword("testpassword");
        player.setGameName("testgame");
        when(playerRepo.save(any(Player.class))).thenReturn(player);
        Player savedPlayer = playerService.savePlayer(player);
        verify(playerRepo, times(1)).save(any(Player.class));
        assertEquals(player,savedPlayer);
    }
}
