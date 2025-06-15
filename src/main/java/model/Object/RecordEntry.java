package model.Object;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RecordEntry {
    private final SimpleStringProperty username;
    private final SimpleIntegerProperty points;
    private final SimpleIntegerProperty time;

    public RecordEntry(String username, int points, int time) {
        this.username = new SimpleStringProperty(username);
        this.points = new SimpleIntegerProperty(points);
        this.time = new SimpleIntegerProperty(time);
    }

    public String getUsername() {
        return username.get();
    }

    public int getPoints() {
        return points.get();
    }

    public int getTime() {
        return time.get();
    }
}
