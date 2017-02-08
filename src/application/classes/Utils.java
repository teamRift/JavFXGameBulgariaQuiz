package application.classes;

import java.time.LocalTime;

public class Utils {
    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
//    public void log(String input){
//        System.out.printf("%s.class %s %s.\n",
//                this.getClass().getSimpleName(), LocalTime.now(), input);
//    }
}
