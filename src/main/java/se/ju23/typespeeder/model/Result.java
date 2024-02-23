/*
 * Class: RankingTableByLevel
 * Description: A class that creates result based on the players performance.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-14
 */
package se.ju23.typespeeder.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long completionSpeed;

    private int correctAnswers;

    private int correctAnswersInRow;

    @ManyToOne
    @JoinColumn(name = "playername", referencedColumnName = "id")
    private Player player;

    public Result(long completionSpeed, int correctAnswers, int correctAnswersInRow, Player player) {
        this.completionSpeed = completionSpeed;
        this.correctAnswers = correctAnswers;
        this.correctAnswersInRow = correctAnswersInRow;
        this.player = player;
    }

    public Result() {

    }

    public long getCompletionSpeed() {
        return completionSpeed;
    }

    public void setCompletionSpeed(long completionSpeed) {
        this.completionSpeed = completionSpeed;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getCorrectAnswersInRow() {
        return correctAnswersInRow;
    }

    public void setCorrectAnswersInRow(int correctAnswersInRow) {
        this.correctAnswersInRow = correctAnswersInRow;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", completionSpeed=" + completionSpeed +
                ", correctAnswers=" + correctAnswers +
                ", correctAnswersInRow=" + correctAnswersInRow +
                ", player=" + player +
                '}';
    }
}
