package se.ju23.typespeeder;

import java.util.List;
import java.util.Optional;

public class DAOManager {
    private PlayerRepo playerRepo;
    private RankingsRepo rankingsRepo;
    private TypeGoalTextRepo typeGoalTextRepo;
    private Player player;
    private AdminMessageRepo adminMessageRepo;

    public void createAdminMessage(AdminMessage adminMessage){
        adminMessageRepo.save(adminMessage);
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setRankingsRepo(RankingsRepo rankingsRepo) {
        this.rankingsRepo = rankingsRepo;
    }

    public void setAdminMessageRepo(AdminMessageRepo adminMessageRepo) {
        this.adminMessageRepo = adminMessageRepo;
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
        player.setRanking(getAverageRankFromPlayer(player.getId()));
        player.setGamesPlayed(player.getGamesPlayed() + 1);
        playerRepo.save(player);
    }
    public void addGame(int rank) {
        Rankings rankings = new Rankings(player.getId(), rank);
        rankingsRepo.save(rankings);
    }
    public void incrementPlayerLevel() {
        player.setLevel(player.getLevel() + 1);
        player.setXp(0);
        playerRepo.save(player);
    }
    public void changeUsername(String newUsername) {
        player.setUserName(newUsername);
        playerRepo.save(player);
    }
    public void changeDisplayName(String newDisplayname){
        player.setDisplayName(newDisplayname);
        playerRepo.save(player);
    }
    public void changePassword(String newPassword){
        player.setPassword(newPassword);
        playerRepo.save(player);
    }
    public int getAverageRankFromPlayer(int playerId) {
        List<Integer> ranks = playerRepo.findPlayerRanksPerGame(playerId);
        Optional<Player> optionalPlayer = playerRepo.findById(playerId);

        if (optionalPlayer.isEmpty()) {
            return 0;
        }

        Player p = optionalPlayer.get();

        int rank = 0;

        for (int i = 0; i < ranks.size(); i++) {
            rank += ranks.get(i);
        }

        return rank / p.getGamesPlayed();
    }
}
