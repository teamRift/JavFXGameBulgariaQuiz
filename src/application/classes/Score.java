package application.classes;

public class Score {

    private String id;
    private String userName;
    private String cityName;
    private String difficulty;
    private String dateSet;
    private int scores;

    public Score(){}
    public Score(String cityName, String userName, int scores) {
        this.setCityName(Utils.capitalize(cityName));
        this.setUserName(userName);
        this.setScore(scores);
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getDate(){
        return this.dateSet;
    }
    public void setDate(String date){
        this.dateSet = date;
    }

    public void setScore(int scores) {
        this.scores = scores;
    }

    public void setCityName(String city){
        this.cityName = city;
    }
    public String getCityName(){ return cityName;}

    public String getUserName(){ return userName; }
    public void setUserName(String userName){ this.userName = userName;}

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getValue() { return scores; }
    public String prepareSave() {

        return String.format("%s ; %s ; %s", cityName, userName, scores);

    }

}

