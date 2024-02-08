package se.ju23.typespeeder.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int result;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    public Result() {}

    public Result(int result, User user) {
        this.result = result;
        this.user = user;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", result=" + result +
                ", user=" + user +
                '}';
    }


}

