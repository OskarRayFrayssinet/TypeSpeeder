package se.ju23.typespeeder;

import java.util.List;

public class DAOManager {
    private PlayerRepo playerRepo;
    private TypeGoalTextRepo typeGoalTextRepo;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTypeGoalTextRepo(TypeGoalTextRepo typeGoalTextRepo) {
        this.typeGoalTextRepo = typeGoalTextRepo;
    }

    public void setPlayerRepo(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> top10LevelPlayers(){
        return playerRepo.findTop10levelPlayers();
    }
    public List<String> fetchGoalText(boolean isSwedish, int amount) {
        if (isSwedish) {
            return typeGoalTextRepo.getSwedishWords(amount);
        } else {
            return typeGoalTextRepo.getEnglishWords(amount);
        }
    }
    public void updatePlayerStats(int xp, int ranking) {
        player.setXp(player.getXp() + xp);
        player.setRanking(ranking);
        player.setGamesPlayed(player.getGamesPlayed() + 1);
        playerRepo.save(player);
    }
    public void incrementPlayerLevel() {
        player.setLevel(player.getLevel() + 1);
        player.setXp(0);
        playerRepo.save(player);
    }
}
