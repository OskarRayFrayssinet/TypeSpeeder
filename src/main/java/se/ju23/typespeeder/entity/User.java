package se.ju23.typespeeder.entity;


public class User {

    private long userid;
    private String username;
    private String password;
    private String playername;
    private long experience;
    private int level;
    private boolean isAdmin;

    public User(long userid,String username, String password) {
        this.userid = userid;
        this.password = password;
    }

    public long getUserid() {
        return userid;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin =admin;
    }
}
