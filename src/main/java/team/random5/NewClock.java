package team.random5;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Calendar;

public class NewClock extends Application {
    private Line hourHand;
    private Line minuteHand;
    private Line secondHand;
    double clockRadius = 110; // added default value for clock radius
    double centerX;
    double centerY;

    private Label digitimeLabel;


    @Override
    public void start(Stage primaryStage) {
        //JavaFX Setup
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Random");
        primaryStage.setScene(scene);
        primaryStage.show();


        // Big Circle as Base
        Circle clockCircle = new Circle();
        clockCircle.centerXProperty().bind(pane.widthProperty().divide(2));
        clockCircle.centerYProperty().bind(pane.heightProperty().divide(2));
        clockCircle.setRadius(clockRadius); // added radius to the clockCircle
        clockCircle.setFill(Color.GOLD);
        centerX = clockCircle.getCenterX();
        centerY = clockCircle.getCenterY();
        pane.getChildren().add(clockCircle);

        // Big Circle
        Circle circle = new Circle();
        circle.setCenterX(centerX + 4);
        circle.setCenterY(centerY + 5);
        circle.setRadius(127);
        circle.setFill(Color.BLACK);
        pane.getChildren().add(circle);

        // Draw clock ticks
        for (int i = 0; i < 12; i++) {
            double angle = i * 30;
            double x = (clockCircle.getCenterX() + clockCircle.getRadius() * Math.sin(Math.toRadians(angle))) - 3;
            double y = (clockCircle.getCenterY() - clockCircle.getRadius() * Math.cos(Math.toRadians(angle))) - 4;
            String tickLabel = String.valueOf(i == 0 ? 12 : i);
            Label label = new Label(tickLabel);
            label.setTextFill(Color.WHITE);
            //label.setFont(Font.font("Helvetica"));
            label.setFont(Font.font("Helvetica", FontWeight.BLACK, 15));
            label.setLayoutX(x - label.getWidth() / 2);
            label.setLayoutY(y - label.getHeight() / 2);
            pane.getChildren().add(label);
        }

        // Create clock hands:
        // Hours hand
        hourHand = new Line();
        hourHand.setStroke(Color.WHITE);
        hourHand.setStrokeWidth(10);
        hourHand.setStrokeLineCap(StrokeLineCap.ROUND);
        hourHand.setStartX(centerX);
        hourHand.setStartY(centerY);
        hourHand.setEndX(centerX);
        hourHand.setEndY(centerY - clockRadius * 0.6);
        pane.getChildren().add(hourHand);

        // Minute hand
        minuteHand = new Line();
        minuteHand.setStroke(Color.WHITE);
        minuteHand.setStrokeWidth(10);
        minuteHand.setStrokeLineCap(StrokeLineCap.ROUND);
        minuteHand.setStartX(centerX);
        minuteHand.setStartY(centerY);
        minuteHand.setEndX(centerX);
        minuteHand.setEndY(centerY - clockRadius * 0.7);
        pane.getChildren().add(minuteHand);

        // Second hand
        secondHand = new Line();
        secondHand.setStroke(Color.TOMATO);
        secondHand.setStrokeWidth(4);
        secondHand.setStrokeLineCap(StrokeLineCap.ROUND);
        secondHand.setStartX(centerX);
        secondHand.setStartY(centerY);
        secondHand.setEndX(centerX);
        secondHand.setEndY(centerY - clockRadius * 0.8);
        pane.getChildren().add(secondHand);


        // Create rotation transforms for clock hands
        Rotate hourRotation = new Rotate(0, centerX, centerY);
        hourHand.getTransforms().add(hourRotation);
        Rotate minuteRotation = new Rotate(0, centerX, centerY);
        minuteHand.getTransforms().add(minuteRotation);
        Rotate secondRotation = new Rotate(0, centerX, centerY);
        secondHand.getTransforms().add(secondRotation);

        // Little Circle
        Circle clockNub = new Circle();
        clockNub.setCenterX(centerX);
        clockNub.setCenterY(centerY);
        clockNub.setRadius(8);
        clockNub.setFill(Color.GOLDENROD);
        pane.getChildren().add(clockNub);


        // Create a label to display the current time in digits
        digitimeLabel = new Label();
        double x = clockCircle.getCenterX() * 0.9;
        double y = clockCircle.getCenterY() * 0.9;
        digitimeLabel.setLayoutX(x - digitimeLabel.getWidth() / 2);
        digitimeLabel.setLayoutY((y + clockRadius * 0.7) + 130);
        digitimeLabel.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 15));
        digitimeLabel.setTextFill(Color.BLACK);

        pane.getChildren().add(digitimeLabel);

        // Create animation timer to update clock hands and time label
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                updateClockHands(hour, minute, second);
                digitimeLabel.setText(String.format("%02d:%02d:%02d", hour, minute, second));
            }
        };
        timer.start();

    }

    private void updateClockHands(double hour, double minute, double second) {
        double hourAngle = (hour % 12 + minute / 60.0) * 30;
        double minuteAngle = (minute + second / 60.0) * 6;
        double secondAngle = second * 6;

        ((Rotate) hourHand.getTransforms().get(0)).setAngle(hourAngle);
        ((Rotate) minuteHand.getTransforms().get(0)).setAngle(minuteAngle);
        ((Rotate) secondHand.getTransforms().get(0)).setAngle(secondAngle);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
