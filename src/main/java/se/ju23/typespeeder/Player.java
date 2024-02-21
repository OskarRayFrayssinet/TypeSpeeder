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
    @Column(name = "gamesplayed")
    private int gamesPlayed;

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
