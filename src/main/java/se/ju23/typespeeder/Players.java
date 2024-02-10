package se.ju23.typespeeder;

import jakarta.persistence.*;

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

    public Players() {
    }

    public Players(int id, String nickname, String username, String password, int level ){
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                '}';
    }
}
