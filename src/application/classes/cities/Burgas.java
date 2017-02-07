package application.classes.cities;

public class Burgas extends City{

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }
    public String fileName() {
        return "burgasQuestions.txt";
    }
}
