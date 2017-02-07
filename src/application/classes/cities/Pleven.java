package application.classes.cities;

public class Pleven extends City {

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "plevenQuestions.txt";
    }
}
