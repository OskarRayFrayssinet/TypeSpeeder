/*
 * Class: RankingTableByTotalPoints
 * Description: A class that creates a view for ranking purposes.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-23
 */
package se.ju23.typespeeder.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name ="playerrankingpoints")
public class RankingTableByTotalPoints{
    @Id
    private int id;
    @Column(name = "totalpoints")
    private String totalPoints;
    private String gamertag;
    private int ranking;

    @Override
    public String toString() {
        return  "\n\033[1mRanking:\033[0m " + ranking + " | " +
                "\033[1mGamertag:\033[0m " + gamertag + " | " +
                "\033[1mLevel:\033[0m " + totalPoints;
    }



}