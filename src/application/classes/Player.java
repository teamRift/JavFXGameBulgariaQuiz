package application.classes;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player {

    private String id;
    private String userName;
    private List scores;
    private String dateCreated;

    public Player(){
        this.scores = new ArrayList<Score>();
        this.dateCreated = new Date().toString();
        System.out.println(this.dateCreated);
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score) {
        this.scores.add(score);
    }
}
