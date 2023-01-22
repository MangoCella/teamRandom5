package team.random5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;


public class AnalogClockTwo extends Application {
    /*public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void start(Stage primaryStage) {
        Pane pane_object = new Pane();

        EventHandler<ActionEvent> eventHandler = e -> {
            SimpleDateFormat simple_date_formater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Calendar cal = new GregorianCalendar();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            if (hour > 12) {
                hour = hour - 12;
            }
            double arc_2 = 30 * hour;
            double arc_3 = 6 * minute;
            Circle circle_object = new Circle();
            circle_object.centerXProperty().bind(pane_object.widthProperty().divide(2));
            circle_object.centerYProperty().bind(pane_object.heightProperty().divide(2));
            circle_object.setRadius(130);
            circle_object.setStroke(Color.CHOCOLATE);
            circle_object.setFill(Color.PINK);
            pane_object.getChildren().add(circle_object);
            Text label_text24 = new Text(340, 250, "3");
            label_text24.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text24);
            Text label_text3 = new Text(250, 350, "6");
            label_text3.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text3);
            Text label = new Text(150, 250, "9");
            label.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label);
            Text label_text = new Text(250, 165, "12");
            label_text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text);
            Text info_text = new Text(210, 200, "Analog Clock");
            info_text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(info_text);
            Text ltext = new Text(150, 450, simple_date_formater.format(cal.getTime()));
            ltext.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
            pane_object.getChildren().add(ltext);

            Line first_line = new Line(250, 200, 250, 250);
            first_line.endXProperty().bind(pane_object.widthProperty().subtract(250));
            first_line.endYProperty().bind(pane_object.heightProperty().subtract(250));
            first_line.setStrokeWidth(5);
            first_line.setStroke(Color.BROWN);
            Line second_line = new Line(250, 180, 250, 250);
            second_line.endXProperty().bind(pane_object.widthProperty().subtract(250));
            second_line.endYProperty().bind(pane_object.heightProperty().subtract(250));
            second_line.setStrokeWidth(5);
            second_line.setStroke(Color.BLACK);
            Rotate r1 = new Rotate();
            Rotate r2 = new Rotate();
            Rotate r3 = new Rotate();
            r1.setAngle(arc_2);
            r1.setPivotX(250);
            r1.setPivotY(250);
            first_line.getTransforms().add(r1);
            pane_object.getChildren().add(first_line);
            r2.setAngle(arc_3);
            r2.setPivotX(250);
            r2.setPivotY(250);
            second_line.getTransforms().add(r2);
            double x_sec = 250 + (100 * 0.8) * Math.sin(second * (2 * Math.PI / 60));
            double y_sec = 250 - (100 * 0.8) * Math.cos(second * (2 * Math.PI / 60));
            Line l4 = new Line(250, 250, x_sec, y_sec);
            pane_object.getChildren().add(second_line);
            pane_object.getChildren().clear();
            pane_object.getChildren().addAll(circle_object, first_line, second_line, l4, label_text, label_text24, label_text3, label, ltext, info_text);
        };
        Timeline time_animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        time_animation.setCycleCount(Timeline.INDEFINITE);
        time_animation.play();
        Scene scene_obj = new Scene(pane_object, 500, 500);
        primaryStage.setTitle("Analog Clock 2");
        primaryStage.setScene(scene_obj);

        primaryStage.show();
    }
}