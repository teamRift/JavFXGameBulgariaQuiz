package application.classes.cities;

public class CityManager {
    private City city;
    public CityManager(){}
    public CityManager(City city){
        this.setCity(city);
    }
    public void setCity(City city){
        if (city!=null) this.city = city;
        else this.city = new Mordor();
    }
    public City getCity(){
        return this.city;
    }
    public String getCityName(){
        return this.city.getClass().getSimpleName();
    }
    public String getFileName(){
        return this.city.fileName();
    }
}
