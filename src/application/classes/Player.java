package application.classes;

import java.util.List;

public class Player {

    private String id;
    private String name;
    private String  email;
    private String password;
    private List<Score> scores;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public List<Score> getScores() {
        return scores;
    }
    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
