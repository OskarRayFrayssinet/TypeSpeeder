package se.ju23.typespeeder.entity;

public class Result {
    private int userid;
    private int result;

    private User user;

    public Result(int userid, int result) {
        this.userid = userid;
        this.result = result;
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

    public int getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return "Result{" +
                "userid=" + userid +
                ", result=" + result +
                ", user=" + user +
                '}';
    }
}
