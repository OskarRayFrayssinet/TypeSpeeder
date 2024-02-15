package se.ju23.typespeeder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class User {
    @Id
    private int id;
    private String username;
    private String password;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static void logIn() {

    }

    public static void logOut() {

    }

    public static void updateProfile() {
    }
}