package se.ju23.typespeeder.classer;

import jakarta.persistence.*;

@Entity
@Table(name = "resultat")
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mistakes")
    private int mistakes;
    @Column(name = "time")
    private Long time;
    @Column(name = "resultat")
    private int resultat;
    @ManyToOne
    @JoinColumn(name = "playerID")
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
