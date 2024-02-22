package se.ju23.typespeeder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="playerrankinglevel")
public class RankingTableByLevel{
    @Id
    private int id;
    private String level;
    private String gamertag;
    private int ranking;

    @Override
    public String toString() {
        return  "\n\033[1mRanking:\033[0m " + ranking + " | " +
                "\033[1mGamertag:\033[0m " + gamertag + " | " +
                "\033[1mLevel:\033[0m " + level;
    }



}