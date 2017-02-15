package application.classes;

public class Score {

    private int scores;

    private String userName;

    private String cityName;

    public Score(){}


    public Score(String cityName, String userName, int scores) {

        this.setCityName(Utils.capitalize(cityName));

        this.setUser(userName);

        this.setScore(scores);
    }
    public Score setScore(int scores) {

        this.scores = scores;

        return this;

    }

    private Score setUser(String user){

        this.userName = user;

        return this;
    }

    private Score setCityName(String city){

        this.cityName = city;

        return this;

    }

    public String getCityName(){ return cityName;}

    public String getUserName(){ return userName; }

    public int getValue() { return scores; }

    public String prepareSave() {

        return String.format("%s ; %s ; %s", cityName, userName, scores);

    }

}

