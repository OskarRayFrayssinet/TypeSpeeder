package se.ju23.typespeeder.gameLogic;

public class UserScores {
    private String username;
    private int level;
    private int xp;
    private double score;
    private String alias;

    public UserScores(String username,String alias, int level, int xp, double score) {
        this.username = username;
        this.alias = alias;
        this.level = level;
        this.xp = xp;
        this.score = score;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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