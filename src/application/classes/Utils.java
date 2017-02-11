package application.classes;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalTime;

public class Utils {

    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public void log(String input) {

        System.out.printf("%s.class %s %s.\n",
                this.getClass().getSimpleName(),
                LocalTime.now(),
                input);

    }

    // this method set the controller scene background
    public static void setBackground(Pane pane, double WIDTH, double HEIGHT) {

        BackgroundImage myBI = new BackgroundImage(new Image(Values.IMG_BACKGROUND,WIDTH,HEIGHT,false,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

    }

    // use this method to set sizes for Panes and Buttons in controllers.
    public static void setSize(Object object, double WIDTH, double HEIGHT) {

        if (object instanceof Pane) {

            ((Pane) object).setMinWidth(WIDTH);

            ((Pane) object).setMaxWidth(WIDTH);

            ((Pane) object).setPrefWidth(WIDTH);

            ((Pane) object).setMinHeight(HEIGHT);

            ((Pane) object).setMaxHeight(HEIGHT);

            ((Pane) object).setPrefHeight(HEIGHT);

        } else if (object instanceof Button) {

            ((Button) object).setMinWidth(WIDTH);

            ((Button) object).setMaxWidth(WIDTH);

            ((Button) object).setPrefWidth(WIDTH);

            ((Button) object).setMinHeight(HEIGHT);

            ((Button) object).setMaxHeight(HEIGHT);

            ((Button) object).setPrefHeight(HEIGHT);

            ((Button) object).setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, 25));


            DropShadow shadow = new DropShadow();

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.GAUSSIAN);

            shadow.setColor(Color.BLACK);


            ((Button) object).setEffect(shadow);

        }else if (object instanceof ImageView) {

            ((ImageView) object).setFitWidth(WIDTH);

            ((ImageView) object).setFitHeight(HEIGHT);

        } else {

            System.out.printf("Utils.setSize(Object object) : %s is not a valid object. Use Pane, ImageView or Button.", Utils.capitalize(object.getClass().getSimpleName()));

        }
    }

    public static void setShadow(Object object) {

        DropShadow shadow = new DropShadow();

        shadow.setColor(Color.BLACK);

        if (object instanceof Label) {

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.ONE_PASS_BOX);

            ((Label) object).setEffect(shadow);

        } else if (object instanceof Button) {

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.GAUSSIAN);

            ((Button) object).setEffect(shadow);

        }

    }

    public static void setShadow(Object object, int offset, int radius) {

        DropShadow shadow = new DropShadow();

        shadow.setColor(Color.BLACK);

        if (object instanceof Label) {

            shadow.setRadius(radius);

            shadow.setOffsetX(offset);

            shadow.setOffsetY(offset);

            shadow.setBlurType(BlurType.ONE_PASS_BOX);

        } else if (object instanceof Button) {

            shadow.setRadius(radius);

            shadow.setOffsetX(offset);

            shadow.setOffsetY(offset);

            shadow.setBlurType(BlurType.GAUSSIAN);


        }

    }

    // sets button background and font style
    public static void styleCityButton(Button... buttons) {

        for (Button button : buttons) {

            button.setBackground(Background.EMPTY);

            button.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD,25));

            button.setTextFill(Color.WHITESMOKE);

            Utils.setShadow(button);

        }
    }
    public static void styleButton(Button... buttons) {

        for (Button button : buttons) {

            button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),new CornerRadii(10), new Insets(5,5,5,5))));

            button.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD,25));

            button.setTextFill(Color.BLACK);

            Utils.setShadow(button);

        }
    }
}
