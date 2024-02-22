package se.ju23.typespeeder.database;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nickname")
    private String nickname;
    @Column (name = "username")
    private String username;
    @Column (name = "password")
    private String password;
    @Column (name = "level")
    private int level;
    @Column (name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private List<Resultat>resultat = new ArrayList<>();

    public Players() {
    }

    public Players(String nickname, String username, String password, int level, String role ){
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.level = level;
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Resultat>getResultat() {
        return resultat;
    }

    public void setResultat(List<Resultat> resultat) {
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", role='" + role + '\'' +
                '}';
    }
}
