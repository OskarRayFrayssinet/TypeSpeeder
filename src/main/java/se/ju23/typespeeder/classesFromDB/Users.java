package se.ju23.typespeeder.classesFromDB;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Entity
@Component public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "email", nullable = false, length = 200)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "alias", nullable = false, length = 45)
    private String alias;
    @Basic
    @Column(name = "xp", nullable = false)
    private int xp;
    @Basic
    @Column(name = "level", nullable = false)
    private int level;

    @OneToMany(mappedBy = "usersByUserId")
    private Collection<Attempt> attemptsByUserId;

    public Users() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (userId != users.userId) return false;
        if (xp != users.xp) return false;
        if (level != users.level) return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (alias != null ? !alias.equals(users.alias) : users.alias != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", alias='" + alias + '\'' +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }

    public Collection<Attempt> getAttemptsByUserId() {
        return attemptsByUserId;
    }

    public void setAttemptsByUserId(Collection<Attempt> attemptsByUserId) {
        this.attemptsByUserId = attemptsByUserId;
    }
}
