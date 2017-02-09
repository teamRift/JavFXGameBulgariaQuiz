package application.classes;

public class Score {
    private int scores;
    private String userName;
    private String cityName;
    public Score(){}
    public Score(String cityName, String userName, int scores) {
        this.cityName = Utils.capitalize(cityName);
        this.userName = userName;
        this.scores = scores;
    }
    public Score setScore(int scores) {
        this.scores = scores;
        return this;
    }
    public Score setUser(String user){
        this.userName = user;
        return this;
    }
    public Score setCityName(String city){
        this.cityName = city;
        return this;
    }
    public String getCityName(){ return cityName;}
    public String getUserName(){ return userName; }
    public int getValue() { return scores; }
    public String prepareSave() {
        //todo get current city name and save
        return String.format("%s ; %s ; %s", cityName, userName, scores);
    }
}