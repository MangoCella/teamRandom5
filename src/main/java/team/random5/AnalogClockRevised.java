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

public class AnalogClockRevised extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane_object = new Pane();
        Scene scene_obj = new Scene(pane_object, 500, 500);
        primaryStage.setTitle("Analog Clock 3");
        primaryStage.setScene(scene_obj);
        primaryStage.show();

        EventHandler<ActionEvent> eventHandler = e -> {
            SimpleDateFormat simple_date_formater = new SimpleDateFormat("dd.MM.yyyy" + "     " + "HH:mm:ss");
            Calendar cal = new GregorianCalendar();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);

            if (hour > 12) {
                hour = hour - 12;
            }

            double arc_2 = 30 * hour;
            double arc_3 = 6 * min;

            Circle circle_object = new Circle();
            circle_object.centerXProperty().bind(pane_object.widthProperty().divide(2));
            circle_object.centerYProperty().bind(pane_object.heightProperty().divide(2));
            circle_object.setRadius(111);
            circle_object.setStroke(Color.TRANSPARENT);
            circle_object.setFill(Color.ALICEBLUE);
            pane_object.getChildren().add(circle_object);

            // shows the numbers of the clock 1-12)
            Text label_text21 = new Text(295, 178, "1");
            label_text21.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text21);

            Text label_text22 = new Text(322, 205, "2");
            label_text22.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text22);

            Text label_text24 = new Text(340, 250, "3");
            label_text24.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text24);

            Text label_text25 = new Text(327, 295, "4");
            label_text25.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text25);

            Text label_text26 = new Text(300, 330, "5");
            label_text26.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text26);

            Text label_text3 = new Text(250, 350, "6");
            label_text3.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text3);

            Text label_text4 = new Text(195, 330, "7");
            label_text4.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text4);

            Text label_text5 = new Text(167, 295, "8");
            label_text5.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text5);

            Text label = new Text(150, 250, "9");
            label.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label);

            Text label2 = new Text(165, 205, "10");
            label2.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label2);

            Text label11 = new Text(194, 175, "11");
            label11.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label11);

            Text label_text = new Text(250, 165, "12");
            label_text.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane_object.getChildren().add(label_text);

            // shows digital Date and Time at the bottom
            Text ltext = new Text(150, 400, simple_date_formater.format(cal.getTime()));
            ltext.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, FontPosture.ITALIC, 20));
            pane_object.getChildren().add(ltext);

            // Shows the Hour hand line (red)
            Line first_line = new Line(250, 200, 250, 250);
            first_line.endXProperty().bind(pane_object.widthProperty().subtract(250));
            first_line.endYProperty().bind(pane_object.heightProperty().subtract(250));
            first_line.setStrokeWidth(5);
            first_line.setStroke(Color.RED);

            // Shows the Minute hand line (dark blue)
            Line second_line = new Line(250, 180, 250, 250);
            second_line.endXProperty().bind(pane_object.widthProperty().subtract(250));
            second_line.endYProperty().bind(pane_object.heightProperty().subtract(250));
            second_line.setStrokeWidth(5);
            second_line.setStroke(Color.DARKBLUE);

            Rotate r1 = new Rotate();
            Rotate r2 = new Rotate();

            r1.setAngle(arc_2);
            r1.setPivotX(250);
            r1.setPivotY(250);
            first_line.getTransforms().add(r1);
            pane_object.getChildren().add(first_line);

            r2.setAngle(arc_3);
            r2.setPivotX(250);
            r2.setPivotY(250);
            second_line.getTransforms().add(r2);

            double x_sec = 250 + (100 * 0.8) * Math.sin(sec * (2 * Math.PI / 60));
            double y_sec = 250 - (100 * 0.8) * Math.cos(sec * (2 * Math.PI / 60));
            Line l4 = new Line(250, 250, x_sec, y_sec);

            pane_object.getChildren().add(second_line);
            pane_object.getChildren().clear();
            pane_object.getChildren().addAll(circle_object, first_line, label_text21, label11, label2, label_text3, label_text4, label_text25, label_text26, label_text22, second_line, l4, label_text, label_text5, label, ltext, label_text24);
        };

        Timeline time_animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        time_animation.setCycleCount(Timeline.INDEFINITE);
        time_animation.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}