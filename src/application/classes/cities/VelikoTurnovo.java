package application.classes.cities;

/**
 * Created by Administrator on 2/7/2017.
 */
public class VelikoTurnovo extends City {

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "vtQuestions.txt";
    }
}
