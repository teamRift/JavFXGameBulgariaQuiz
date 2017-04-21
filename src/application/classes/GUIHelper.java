package application.classes;

import application.constants.ConstantsDimensions;
import application.constants.ConstantsPath;
import application.constants.ConstantsStyle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GUIHelper {
    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static void setBackground(Pane pane, double WIDTH, double HEIGHT) {
        BackgroundImage myBI = new BackgroundImage(new Image(ConstantsPath.IMG_BACKGROUND, WIDTH, HEIGHT,false,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));
    }

    public static void styleLabel(int size, Label... labels) {
        for (Label label : labels) {
            label.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD,size));
            label.setTextFill(Color.WHITE);
            setShadow(label);
        }
    }

    public static void setViewDimensions(Object object, double WIDTH, double HEIGHT) {
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
            ((Button) object).setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H3));
            styleButton((Button) object);
        } else if (object instanceof Label) {
            ((Label) object).setMinWidth(WIDTH);
            ((Label) object).setPrefWidth(WIDTH);
            ((Label) object).setMaxWidth(WIDTH);
            ((Label) object).setMinHeight(HEIGHT);
            ((Label) object).setPrefHeight(HEIGHT);
            ((Label) object).setMaxHeight(HEIGHT);
        } else if (object instanceof ImageView) {
            ((ImageView) object).setFitWidth(WIDTH);
            ((ImageView) object).setFitHeight(HEIGHT);
        } else if (object instanceof TextField) {
            ((TextField) object).setMinWidth(WIDTH);
            ((TextField) object).setMaxWidth(WIDTH);
            ((TextField) object).setPrefWidth(WIDTH);
            ((TextField) object).setMinHeight(HEIGHT);
            ((TextField) object).setMaxHeight(HEIGHT);
            ((TextField) object).setPrefHeight(HEIGHT);
            ((TextField) object).setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H3));
        } else {
            System.out.printf("setViewDimensions(Object object) : %s is not a valid object. Pane, ImageView, Label or Button required.", capitalize(object.getClass().getSimpleName()));
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
        } else if (object instanceof HBox) {
            shadow.setRadius(-5);
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            shadow.setBlurType(BlurType.GAUSSIAN);
            ((HBox) object).setEffect(shadow);
        }
    }

    public static void styleCityButton(Button... buttons) {
        for (Button button : buttons) {
            button.setBackground(Background.EMPTY);
            button.setFont(Font.font(ConstantsStyle.DEFAULT_FONT,FontWeight.BOLD, ConstantsDimensions.H3));
            button.setTextFill(Color.WHITESMOKE);
            setShadow(button);
        }
    }

    public static void styleButton(Button... buttons) {
        for (Button button : buttons) {
            button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),new CornerRadii(7), new Insets(5,5,5,5))));
            button.setFont(Font.font(ConstantsStyle.DEFAULT_FONT,FontWeight.BOLD, ConstantsDimensions.H3));
            button.setTextFill(Color.BLACK);
            setShadow(button);
        }
    }

    public static void styleAsButton(Object... objects) {
        for (Object object : objects) {
            if (object instanceof HBox){
                ((HBox) object).setBackground(
                        new Background(
                                new BackgroundFill(Paint.valueOf("#FFFFFF"),
                                        new CornerRadii(7),
                                        new Insets(5))));
                setShadow(((HBox) object));
            } else if (object instanceof Label) {
                ((Label) object).setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H3));
                ((Label) object).setTextFill(Color.BLACK);
            }
        }
    }
}
