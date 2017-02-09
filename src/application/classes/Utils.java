package application.classes;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.time.LocalTime;

public class Utils {
    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    public void log(String input){
        System.out.printf("%s.class %s %s.\n",
                this.getClass().getSimpleName(), LocalTime.now(), input);
    }
    // this method set the controller scene background
    public static void setBackground(Pane pane, double WIDTH, double HEIGHT){
        BackgroundImage myBI = new BackgroundImage(new Image(Values.IMG_BACKGROUND,WIDTH,HEIGHT,false,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }
    // use this method to set sizes for Panes and Buttons in controllers.
    public static void setSize(Object object, double WIDTH, double HEIGHT){
        if (object instanceof Pane){
            ((Pane) object).setMinWidth(WIDTH);
            ((Pane) object).setMaxWidth(WIDTH);
            ((Pane) object).setPrefWidth(WIDTH);
            ((Pane) object).setMinHeight(HEIGHT);
            ((Pane) object).setMaxHeight(HEIGHT);
            ((Pane) object).setPrefHeight(HEIGHT);
        } else if (object instanceof Button){
            ((Button) object).setMinWidth(WIDTH);
            ((Button) object).setMaxWidth(WIDTH);
            ((Button) object).setPrefWidth(WIDTH);
            ((Button) object).setMinHeight(HEIGHT);
            ((Button) object).setMaxHeight(HEIGHT);
            ((Button) object).setPrefHeight(HEIGHT);
        }else if (object instanceof ImageView){
            ((ImageView) object).setFitWidth(WIDTH);
            ((ImageView) object).setFitHeight(HEIGHT);
        } else {
            System.out.printf("Utils.setSize(Object object) : %s is not a valid object. Use Pane, ImageView or Button.", Utils.capitalize(object.getClass().getSimpleName()));
        }
    }
}
