package se.ju23.typespeeder;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Attempt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    @Column(name = "attempt_id", nullable = false)
    private int attemptId;
    @Basic
    @Column(name = "user_id", insertable=false, updatable=false)
    //@Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "task_id", insertable=false, updatable=false)
    //@Column(name = "task_id", nullable = false)
    private int taskId;
    @Basic
    @Column(name = "total_points", nullable = false)
    private int totalPoints;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Users usersByUserId;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id", nullable = false)
    private Tasks tasksByTaskId;
    @OneToMany(mappedBy = "attemptByAttemptId")
    private Collection<PointParam> pointParamsByAttemptId;

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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = attemptId;
        result = 31 * result + userId;
        result = 31 * result + taskId;
        result = 31 * result + totalPoints;
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
