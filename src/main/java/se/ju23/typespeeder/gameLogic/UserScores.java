package se.ju23.typespeeder.gameLogic;

public class UserScores {
    private String username;
    private int level;
    private int xp;
    private double score;

    public UserScores(String username, int level, int xp, double score) {
        this.username = username;
        this.level = level;
        this.xp = xp;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public double getScore() {
        return score;
    }
}