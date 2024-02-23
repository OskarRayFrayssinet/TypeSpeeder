package se.ju23.typespeeder.classesFromDB;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Attempt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attempt_id", nullable = false)
    private int attemptId;
    @Basic
    @Column(name = "user_id", insertable=false, updatable=false)
    private int userId;
    @Basic
    @Column(name = "task_id", insertable=false, updatable=false)
    private int taskId;
    @Basic
    @Column(name = "total_points", nullable = false)
    private double totalPoints;
    @Basic
    @Column(name = "solution")
    private String solution;
    @Basic
    @Column(name = "user_outcome")
    private String userOutcome;
    @Basic
    @Column(name = "at_time", nullable = false)
    private Timestamp atTime;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Users usersByUserId;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id", nullable = false)
    private Tasks tasksByTaskId;


    @OneToMany(mappedBy = "attemptByAttemptId")
    private Collection<PointParam> pointParamsByAttemptId;

    public Attempt(double totalPoints, String solution, String userOutcome, Users usersByUserId, Tasks tasksByTaskId, Timestamp currentTime) {
        this.totalPoints = totalPoints;
        this.solution = solution;
        this.userOutcome = userOutcome;
        this.usersByUserId = usersByUserId;
        this.tasksByTaskId = tasksByTaskId;
        this.atTime = currentTime;
    }

    public Attempt(String solution, int taskId, double totalPoints, String userOutcome, int userId) {
        this.userId = userId;
        this.taskId = taskId;
        this.totalPoints = totalPoints;
        this.solution = solution;
        this.userOutcome = userOutcome;

    }
    public Attempt() {

    }


    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getUserOutcome() {
        return userOutcome;
    }

    public void setUserOutcome(String userOutcome) {
        this.userOutcome = userOutcome;
    }
    public Timestamp getAtTime() {
        return atTime;
    }

    public void setAtTime(Timestamp atTime) {
        this.atTime = atTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attempt attempt = (Attempt) o;

        if (attemptId != attempt.attemptId) return false;
        if (userId != attempt.userId) return false;
        if (taskId != attempt.taskId) return false;
        if (totalPoints != attempt.totalPoints) return false;
        if (!Objects.equals(solution, attempt.solution)) return false;
        if (!Objects.equals(userOutcome, attempt.userOutcome)) return false;
        if (atTime != null ? !atTime.equals(attempt.atTime) : attempt.atTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attemptId;
        result = 31 * result + userId;
        result = 31 * result + taskId;
        result = (int) (31 * result + totalPoints);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        result = 31 * result + (userOutcome != null ? userOutcome.hashCode() : 0);
        result = 31 * result + (atTime != null ? atTime.hashCode() : 0);
        return result;
    }

    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public Tasks getTasksByTaskId() {
        return tasksByTaskId;
    }

    public void setTasksByTaskId(Tasks tasksByTaskId) {
        this.tasksByTaskId = tasksByTaskId;
    }



    public Collection<PointParam> getPointParamsByAttemptId() {
        return pointParamsByAttemptId;
    }

    public void setPointParamsByAttemptId(Collection<PointParam> pointParamsByAttemptId) {
        this.pointParamsByAttemptId = pointParamsByAttemptId;
    }
}
