package se.ju23.typespeeder.database;

import jakarta.persistence.*;
import se.ju23.typespeeder.database.Players;

@Entity
@Table(name = "resultat")
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "mistakes", nullable = false)
    private int mistakes;
    @Column(name = "time", nullable = false)
    private Long time;
    @Column(name = "resultat",  nullable = false)
    private int resultat;

    @Column(name = "player_id", nullable = false, updatable = false,insertable = false)
    private int playerid;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private Players players;

    public Resultat() {
    }

    public Resultat ( int mistakes, Long time, int resultat, Players players) {

        this.mistakes = mistakes;
        this.time = time;
        this.resultat = resultat;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Players getPlayers() {
        return players;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }
    @Override
    public String toString() {
        return "Resultat{" +
                "id=" + id +
                ", mistakes=" + mistakes +
                ", time=" + time +
                ", resultat=" + resultat +
                ", players=" + players.getNickname() +
                '}';
    }
}
