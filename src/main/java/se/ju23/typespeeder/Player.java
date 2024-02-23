package se.ju23.typespeeder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "displayname")
    private String displayName;
    private String password;
    private int level;
    private int ranking;
    private int xp;
    @Column(name = "gamesplayed")
    private int gamesPlayed;
    private boolean isAdmin;
    @Column(name = "is_admin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getId() {
        return id;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int xp) {
        this.ranking = xp;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUserName() {
        return userName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
}
