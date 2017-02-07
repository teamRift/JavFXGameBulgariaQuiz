package application.classes.cities;

public class Sofia extends City{


    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "sofiaQuestions.txt";
    }
}
