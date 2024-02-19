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
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<Result> gamesPlayed;

    public Player(String username, String password, Role role, int points, int level) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.points = points;
        this.level = level;
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

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", points=" + points +
                ", level=" + level +
                '}';
    }
}
