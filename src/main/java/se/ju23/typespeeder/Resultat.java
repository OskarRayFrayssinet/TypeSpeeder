package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "resultat")
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "resultat")
    private int resultat;
    @Column(name = "playerID")
    private int playerID;

    public Resultat() {
    }

    public  Resultat(int id, int resultat, int playerID){
        this.id = id;
        this.resultat = resultat;
        this.playerID = playerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "id=" + id +
                ", resultat=" + resultat +
                ", playerID=" + playerID +
                '}';
    }
}
