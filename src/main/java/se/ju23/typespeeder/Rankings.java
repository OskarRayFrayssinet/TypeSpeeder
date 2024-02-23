package se.ju23.typespeeder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "games")
@Entity
public class Rankings {
    @Id
    private int id;
    @Column(name = "player")
    private int playerId;
    @Column(name = "rank_per_game")
    private int rank;

    public Rankings(int playerId, int rank) {
        this.playerId = playerId;
        this.rank = rank;
    }

    public Rankings() {

    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
