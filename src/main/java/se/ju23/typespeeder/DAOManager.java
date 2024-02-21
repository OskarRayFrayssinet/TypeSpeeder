package se.ju23.typespeeder;

import java.util.List;

public class DAOManager {
    private PlayerRepo playerRepo;
    private TypeGoalTextRepo typeGoalTextRepo;

    public void setTypeGoalTextRepo(TypeGoalTextRepo typeGoalTextRepo) {
        this.typeGoalTextRepo = typeGoalTextRepo;
    }

    public void setPlayerRepo(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> top10LevelPlayers(){
        return playerRepo.findTop10levelPlayer();
    }
    public List<String> fetchGoalText(boolean isSwedish, int amount) {
        if (isSwedish) {
            return typeGoalTextRepo.getSwedishWords(amount);
        } else {
            return typeGoalTextRepo.getEnglishWords(amount);
        }
    }
}
