package se.ju23.typespeeder.classesFromDB;

import jakarta.persistence.*;

@Entity
@Table(name = "point_param", schema = "typespeeder", catalog = "")
public class PointParam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "attempt_id", insertable=false, updatable=false)
    private int attemptId;
    @Basic
    @Column(name = "speed_in_sec", nullable = false)
    private double speedInSec;
    @Basic
    @Column(name = "correct", nullable = false)
    private int correct;
    @Basic
    @Column(name = "correct_in_order", nullable = false)
    private int correctInOrder;
    @Basic
    @Column(name = "questions", nullable = false)
    private int questions;
    @ManyToOne
    @JoinColumn(name = "attempt_id", referencedColumnName = "attempt_id", nullable = false)
    private Attempt attemptByAttemptId;

    public PointParam(int attemptId, double speedInSec, int correct, int correctInOrder) {
        this.attemptId = attemptId;
        this.speedInSec = speedInSec;
        this.correct = correct;
        this.correctInOrder = correctInOrder;


    }

    public PointParam(double speedInSec, int correct, int correctInOrder, Attempt attemptByAttemptId,int questions) {
        this.speedInSec = speedInSec;
        this.correct = correct;
        this.correctInOrder = correctInOrder;
        this.attemptByAttemptId = attemptByAttemptId;
        this.questions = questions;
    }

    public PointParam() {
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

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

    public double getSpeedInSec() {
        return speedInSec;
    }

    public void setSpeedInSec(double speed) {
        this.speedInSec = speed;
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
        if (speedInSec != that.speedInSec) return false;
        if (questions != that.questions) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + attemptId;
        result = (int) (31 * result + speedInSec);
        result = 31 * result + correct;
        result = 31 * result + correctInOrder;
        result = 31 * result + questions;
        return result;
    }

    public Attempt getAttemptByAttemptId() {
        return attemptByAttemptId;
    }

    public void setAttemptByAttemptId(Attempt attemptByAttemptId) {
        this.attemptByAttemptId = attemptByAttemptId;
    }
}
