/*
 * Class: Player
 * Description: A class that creates a player.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-14
 */
package se.ju23.typespeeder.model;
import jakarta.persistence.*;
import se.ju23.typespeeder.enums.Role;

import java.util.List;

@Entity
@Table(name = "players")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    private int points;
    private int level;

    @Column(name = "totalpoints")
    private int totalPoints;

    @Column (name = "playername")
    private String playerName;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<Result> playerNameList;

    public Player(String username, String password, Role role, int points, int level, String playerName, int totalPoints) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.points = points;
        this.level = level;
        this.playerName = playerName;
        this.totalPoints = totalPoints;
    }

    public Player(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints += totalPoints;
    }

    @Override
    public String toString() {
        return "\n\033[1mPlayerid:\033[0m " + id + " | " + "\033[1mUsername:\033[0m " + username + " | " + "\033[1mPassword:\033[0m " + password + " | " +
                "\033[1mGamertag: \033[0m " + playerName + " | " + "\033[1mPoints:\033[0m " + points + " | " + "\033[1mLevel:\033[0m " + level +  " | " +
                "\033[1mTotal Points:\033[0m " + totalPoints + " | " + "\033[1mRole:\033[0m " + role;
    }
}
