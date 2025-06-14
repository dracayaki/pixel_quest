package model.Object;

public class User {

    private int id;
    private String username;
    private String password;
    private int totalPoints;
    private int lastLevelPlayed;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, int totalPoints, int lastLevelPlayed) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.totalPoints = totalPoints;
        this.lastLevelPlayed = lastLevelPlayed;
    }

    public int getId() {
        return id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getLastLevelPlayed() {
        return lastLevelPlayed;
    }

    public void setLastLevelPlayed(int lastLevelPlayed) {
        this.lastLevelPlayed = lastLevelPlayed;
    }
}
