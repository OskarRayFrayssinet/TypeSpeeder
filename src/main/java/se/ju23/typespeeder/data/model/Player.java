package se.ju23.typespeeder.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "logname")
    private String logName;
    @Column(name = "password")
    private String password;
    @Column(name = "gamename")
    private String gameName;


    public Player(){ }

    public Player(String logName, String password, String gameName){
        this.logName = logName;
        this.password = password;
        this.gameName = gameName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", logName='" + logName + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
