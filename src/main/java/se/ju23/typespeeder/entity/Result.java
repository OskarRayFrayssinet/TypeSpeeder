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
    private int userId;
    // Konstruktor
    public Result(int id, int result, int userId) {
        this.id = id;
        this.result = result;
        this.userId = userId;

    }

    public Result() {}



    // Getter och Setter för id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }



    // Övriga metoder efter behov
}

