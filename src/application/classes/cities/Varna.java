package application.classes.cities;


public class Varna extends City{

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "varnaQuestions.txt";
    }
}
