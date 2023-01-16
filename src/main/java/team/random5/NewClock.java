package team.random5;

import javafx.application.Application;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Calendar;


public class NewClock extends Application {

    private Line hourHand;
    private Line minuteHand;
    private Line secondHand;
    private Label timeLabel;
    private Object object;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Circle clockCircle = new Circle();
        clockCircle.centerXProperty().bind(pane.widthProperty().divide(2));
        clockCircle.centerYProperty().bind(pane.heightProperty().divide(2));
        clockCircle.setRadius(130);
        clockCircle.setStroke(Color.CHOCOLATE);
        clockCircle.setFill(Color.DARKMAGENTA);
        pane.getChildren().add(clockCircle);

        double clockRadius = clockCircle.getRadius();
        double centerX = clockCircle.getCenterX();
        double centerY = clockCircle.getCenterY();

        // Draw clock ticks
        for (int i = 0; i < 12; i++) {
            double angle = i * 30;
            double x = centerX + clockRadius * Math.sin(Math.toRadians(angle));
            double y = centerY - clockRadius * Math.cos(Math.toRadians(angle));
            String tickLabel = String.valueOf(i == 0 ? 12 : i);
            Label label = new Label(tickLabel);
            label.setLayoutX(x - label.getWidth() / 2);
            label.setLayoutY(y - label.getHeight() / 2);
            pane.getChildren().add(label);
        }

        // Create clock hands from paths (with rounded edges)
        // Minute hand
        Path hourHand = new Path();
        hourHand.setStroke(Color.CHOCOLATE);
        hourHand.setStrokeWidth(8);

        double handWidth = 8;
        double handLength = clockRadius * 0.5;


        MoveTo start = new MoveTo(centerX - (handWidth / 2), centerY);
        LineTo line1 = new LineTo(centerX - handWidth/2, centerY - handLength + handWidth/2);
        ArcTo arc1 = new ArcTo(handWidth/2, handWidth/2, 0, centerX + handWidth/2, centerY - handLength, false, true);
        LineTo line2 = new LineTo(centerX + handWidth/2, centerY);
        ArcTo arc2 = new ArcTo(handWidth/2, handWidth/2, 0, centerX - handWidth/2, centerY, false, true);

        hourHand.getElements().addAll(start, line1, arc1, line2, arc2);
        pane.getChildren().add(hourHand);

        // Minute hand
        Path minuteHand = new Path();
        minuteHand.setStroke(Color.BLUE);
        minuteHand.setStrokeWidth(6);

        double minuteHandLength = clockRadius * 0.7;
        double minuteHandWidth = 6;

        if (object != null) {
            // perform operation on object
        MoveTo mStart = new MoveTo(centerX - minuteHandWidth/2, centerY);
        LineTo mLine1 = new LineTo(centerX - minuteHandWidth/2, centerY - minuteHandLength + minuteHandWidth/2);
        ArcTo mArc1 = new ArcTo(minuteHandWidth/2, minuteHandWidth/2, 0, centerX + minuteHandWidth/2, centerY - minuteHandLength + minuteHandWidth/2, false, true);
        LineTo mLine2 = new LineTo(centerX + minuteHandWidth/2, centerY);
        ArcTo mArc2 = new ArcTo(minuteHandWidth/2, minuteHandWidth/2, 0, centerX - minuteHandWidth/2, centerY, false, true);

        minuteHand.getElements().addAll(mStart, mLine1, mArc1, mLine2, mArc2);
        pane.getChildren().add(minuteHand);
        }
        // Second hand
        Path secondHand = new Path();
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(4);

        double secondHandLength = clockRadius * 0.8;
        double secondHandWidth = 4;

        MoveTo sStart = new MoveTo(centerX - secondHandWidth/2, centerY);
        LineTo sLine1 = new LineTo(centerX - secondHandWidth/2, centerY - secondHandLength + secondHandWidth/2);
        ArcTo sArc1 = new ArcTo(secondHandWidth/2, secondHandWidth/2, 0, centerX + secondHandWidth/2, centerY - secondHandLength + secondHandWidth/2, false, true);
        LineTo sLine2 = new LineTo(centerX + secondHandWidth/2, centerY);
        ArcTo sArc2 = new ArcTo(secondHandWidth/2, secondHandWidth/2, 0, centerX - secondHandWidth/2, centerY, false, true);

        secondHand.getElements().addAll(sStart, sLine1, sArc1, sLine2, sArc2);
        pane.getChildren().add(secondHand);

        /*
        // Create clock hands from lines
        MoveTo start = new MoveTo(centerX, centerY);
        LineTo line1 = new LineTo(centerX, centerY - handLength + handWidth/2);
        ArcTo arc1 = new ArcTo(handWidth/2, handWidth/2, 0, centerX + handWidth/2, centerY - handLength + handWidth/2, false, true);
        LineTo line2 = new LineTo(centerX + handWidth/2, centerY);
        ArcTo arc2 = new ArcTo(handWidth/2, handWidth/2, 0, centerX, centerY, false, true);
        */
        /*
        hourHand = new Line();
        hourHand.setStroke(Color.CHOCOLATE);
        hourHand.setStrokeWidth(8);
        hourHand.setStartX(centerX);
        hourHand.setStartY(centerY);
        hourHand.setEndX(centerX);
        hourHand.setEndY(centerY - clockRadius * 0.5);
        pane.getChildren().add(hourHand);

        minuteHand = new Line();
        minuteHand.setStroke(Color.CHOCOLATE);
        minuteHand.setStrokeWidth(6);
        minuteHand.setStartX(centerX);
        minuteHand.setStartY(centerY);
        minuteHand.setEndX(centerX);
        minuteHand.setEndY(centerY - clockRadius * 0.8);
        pane.getChildren().add(minuteHand);

        secondHand = new Line();
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(4);
        secondHand.setStartX(centerX);
        secondHand.setStartY(centerY);
        secondHand.setEndX(centerX);
        secondHand.setEndY(centerY - clockRadius * 0.9);
        pane.getChildren().add(secondHand);

         */

        // Create time label
        timeLabel = new Label();
        timeLabel.setText("00:00:00");
        timeLabel.setLayoutX(centerX - timeLabel.getWidth() / 2);
        timeLabel.setLayoutY(centerY + clockRadius * 0.8);
        pane.getChildren().add(timeLabel);

        // Create rotation transforms for clock hands
        Rotate hourRotation = new Rotate(0, centerX, centerY);
        hourHand.getTransforms().add(hourRotation);
        Rotate minuteRotation = new Rotate(0, centerX, centerY);
        minuteHand.getTransforms().add(minuteRotation);
        Rotate secondRotation = new Rotate(0, centerX, centerY);
        secondHand.getTransforms().add(secondRotation);

        // Create animation timer to update clock hands and time label
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                updateClockHands(hour, minute, second);
                timeLabel.setText(String.format("%02d:%02d:%02d", hour, minute, second));
            }
        };
        timer.start();

        primaryStage.setScene(new Scene(pane, 500, 500));
        primaryStage.setTitle("Analog Clock");
        primaryStage.show();
    }

    private void updateClockHands(double hour, double minute, double second) {
        double hourAngle = (hour % 12 + minute / 60.0) * 30;
        double minuteAngle = (minute + second / 60.0) * 6;
        double secondAngle = second * 6;
        ((Rotate) hourHand.getTransforms().get(0)).setAngle(hourAngle);
        ((Rotate) minuteHand.getTransforms().get(0)).setAngle(minuteAngle);
        ((Rotate) secondHand.getTransforms().get(0)).setAngle(secondAngle);
    }
}


