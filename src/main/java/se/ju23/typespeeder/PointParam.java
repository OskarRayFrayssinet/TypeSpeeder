package se.ju23.typespeeder;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "point_param", schema = "typespeeder", catalog = "")
public class PointParam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "attempt_id", nullable = false)
    private int attemptId;
    @Basic
    @Column(name = "speed", nullable = false)
    private Timestamp speed;
    @Basic
    @Column(name = "correct", nullable = false)
    private int correct;
    @Basic
    @Column(name = "correct_in_order", nullable = false)
    private int correctInOrder;
    @ManyToOne
    @JoinColumn(name = "attempt_id", referencedColumnName = "attempt_id", nullable = false)
    private Attempt attemptByAttemptId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public Timestamp getSpeed() {
        return speed;
    }

    public void setSpeed(Timestamp speed) {
        this.speed = speed;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getCorrectInOrder() {
        return correctInOrder;
    }

    public void setCorrectInOrder(int correctInOrder) {
        this.correctInOrder = correctInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointParam that = (PointParam) o;

        if (id != that.id) return false;
        if (attemptId != that.attemptId) return false;
        if (correct != that.correct) return false;
        if (correctInOrder != that.correctInOrder) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + attemptId;
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + correct;
        result = 31 * result + correctInOrder;
        return result;
    }

    public Attempt getAttemptByAttemptId() {
        return attemptByAttemptId;
    }

    public void setAttemptByAttemptId(Attempt attemptByAttemptId) {
        this.attemptByAttemptId = attemptByAttemptId;
    }
}
