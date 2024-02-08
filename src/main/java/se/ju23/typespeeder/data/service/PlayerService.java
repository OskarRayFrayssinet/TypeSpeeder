package se.ju23.typespeeder.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.data.model.Player;
import se.ju23.typespeeder.data.repo.PlayerRepo;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player savePlayer(Player player){
        return playerRepo.save(player);
    }

    public Player updatePlayer(Player player){
        Player existingPlayer = getPlayerByLogName(player.getLogName());
        if(existingPlayer != null){
            existingPlayer.setLogName(player.getLogName());
            existingPlayer.setPassword(player.getPassword());
            existingPlayer.setGameName(player.getGameName());
            return playerRepo.save(existingPlayer);
        }else{
            return null;
        }
    }

    public void deletePlayer(String logName){
        Player existPlayer = getPlayerByLogName(logName);
        if(existPlayer != null){
            playerRepo.deleteByName(logName);
        }
    }

    public Player getPlayerByLogName(String logName){
        return playerRepo.findByName(logName).orElse(null);
    }

    public List<Player> getAllPlayers(){
        return playerRepo.findAll();
    }
}
