package application.classes;

public class Score {
    private int scores;
    private String userName;
    private String cityName;
    public Score(String cityName, String userName, int scores) {
        this.cityName = Utils.capitalize(cityName);
        this.userName = userName;
        this.scores = scores;
    }
    public int getValue() { return scores; }
    public String prepareSave() {
        //todo get current city name and save
        return String.format("%s ; %s ; %s", cityName, userName, scores);
    }
}