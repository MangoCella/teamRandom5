package team.random5;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
        double clockRadius;
        double centerX;
        double centerY;

        private Label timeLabel;



    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Random");
        primaryStage.setScene(scene);
        primaryStage.show();

        Circle clockCircle = new Circle();
        clockCircle.centerXProperty().bind(pane.widthProperty().divide(2));
        clockCircle.centerYProperty().bind(pane.heightProperty().divide(2));
        clockCircle.setStroke(Color.CHOCOLATE);
        clockCircle.setFill(Color.DARKMAGENTA);
        pane.getChildren().add(clockCircle);

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


        Circle clockNub = new Circle();
        clockNub.centerXProperty().bind(pane.widthProperty().divide(2));
        clockNub.centerYProperty().bind(pane.heightProperty().divide(2));
        clockNub.setRadius(10);
        clockNub.setFill(Color.DIMGREY);
        pane.getChildren().add(clockNub);

        // Hands with rounded edges
        // Hour hand
        hourHand.setStroke(Color.CHOCOLATE);
        hourHand.setStrokeWidth(8);
        double handLength = clockRadius * 0.6;
        double handWidth = 10;

        MoveTo start = new MoveTo(centerX, centerY);
        LineTo line1 = new LineTo(centerX, centerY - handLength + handWidth / 2);
        ArcTo arc1 = new ArcTo(handWidth / 2, handWidth / 2, 0, centerX + handWidth / 2, centerY - handLength, false, true);
        LineTo line2 = new LineTo(centerX + handWidth / 2, centerY);
        ArcTo arc2 = new ArcTo(handWidth / 2, handWidth / 2, 0, centerX - handWidth / 2, centerY, false, true);

        pane.getChildren().add(hourHand);

        // Minute hand
        minuteHand.setStroke(Color.BLUE);
        minuteHand.setStrokeWidth(6);
        double minuteHandLength = clockRadius * 0.9;
        double minuteHandWidth = 10;

        MoveTo mStart = new MoveTo(centerX, centerY);
        LineTo mLine1 = new LineTo(centerX, centerY - minuteHandLength + minuteHandWidth / 2);
        ArcTo mArc1 = new ArcTo(minuteHandWidth / 2, minuteHandWidth / 2, 0, centerX + minuteHandWidth / 2, centerY - minuteHandLength + minuteHandWidth / 2, false, true);
        LineTo mLine2 = new LineTo(centerX + minuteHandWidth / 2, centerY);
        ArcTo mArc2 = new ArcTo(minuteHandWidth / 2, minuteHandWidth / 2, 0, centerX - minuteHandWidth / 2, centerY, false, true);

        secondHand.setStroke(Color.DARKRED);
        secondHand.setStrokeWidth(6);
        double secondHandLength = clockRadius * 0.9;
        double secondHandWidth = 3;

        MoveTo sStart = new MoveTo(centerX, centerY);
        LineTo sLine1 = new LineTo(centerX, centerY - secondHandLength + secondHandWidth / 2);
        ArcTo sArc1 = new ArcTo(secondHandWidth / 2, secondHandWidth / 2, 0, centerX + secondHandWidth / 2, centerY - secondHandLength + secondHandWidth / 2, false, true);
        LineTo sLine2 = new LineTo(centerX + secondHandWidth / 2, centerY);
        ArcTo sArc2 = new ArcTo(secondHandWidth / 2, secondHandWidth / 2, 0, centerX - secondHandWidth / 2, centerY, false, true);
        pane.getChildren().add(secondHand);


// Create a label to display the current time
        timeLabel.setTextFill(Color.CHOCOLATE);
        timeLabel.setLayoutX(centerX - timeLabel.getWidth() / 2);
        timeLabel.setLayoutY(centerY + clockRadius + 20);
        pane.getChildren().add(timeLabel);

// Update clock hands and time label every second
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                updateClockHands(hour, minute, second);
                //timeLabel(hour, minute, second);
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
