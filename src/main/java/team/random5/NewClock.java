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

/**
 * The Application class is the base class for JavaFX applications and provides a framework for the lifecycle
 * of a JavaFX application. By extending this class, the "NewClock" class inherits all the properties and
 * methods of the Application class and can also add or override them.
 */
public class NewClock extends Application {
    private Line hourHand;
    private Line minuteHand;
    private Line secondHand;
    double clockRadius = 110;
    double centerX;
    double centerY;

    private Label digitimeLabel;


    @Override
    public void start(Stage primaryStage) {
        //JavaFX Setup
        /** This empty 500x500 pixel window with the title "Random" is created using the Pane container
         * A Pane is a container for holding other UI elements in a JavaFX application.
         * The scene is assigned to the primaryStage and shown.
         */
        // Empty pane object
        Pane pane = new Pane();
        /** A Scene is the container for all content in a JavaFX application.
         * It is created by passing in the root node (in this case, the pane) and the width and height of the scene.
         */
        Scene scene = new Scene(pane, 500, 500);
        //The primaryStage is a window in the JavaFX application
        primaryStage.setTitle("Random");
        //This line sets the scene on the primary stage, so that it will be displayed when the stage is shown.
        primaryStage.setScene(scene);
        //This line is used to show the primary stage and make it visible to the user.
        primaryStage.show();

        // Big Circle as Base
        Circle clockCircle = new Circle();
        //binds the centerX property of the clockCircle object to half of the width of the pane
        clockCircle.centerXProperty().bind(pane.widthProperty().divide(2));
        //binds the centerY property of the clockCircle object to half of the height of the pane
        clockCircle.centerYProperty().bind(pane.heightProperty().divide(2));
        clockCircle.setRadius(clockRadius); // added radius to the clockCircle
        clockCircle.setFill(Color.GOLD);
        centerX = clockCircle.getCenterX() - 3;
        centerY = clockCircle.getCenterY();
        pane.getChildren().add(clockCircle);

        // Big Circle
        Circle circle = new Circle();
        circle.setCenterX(centerX + 4);
        circle.setCenterY(centerY + 5);
        circle.setRadius(130);
        circle.setFill(Color.BLACK);
        pane.getChildren().add(circle);

        // Draw clock ticks on their designated spots
        for (int i = 0; i < 12; i++) {
            double angle = i * 30;
            double x = (clockCircle.getCenterX() + clockCircle.getRadius() * Math.sin(Math.toRadians(angle))) -2;
            double y = (clockCircle.getCenterY() - clockCircle.getRadius() * Math.cos(Math.toRadians(angle))) - 4;
            /**
             The ternary operator ? :  is a shorthand way of writing an if-else statement.
             The expression is checking if the value of i is equal to 0,if that's true
             it assigns the value 12 to the tickLabel otherwise it assigns the value of i to the tickLabel.
             The String.valueOf() method is used to convert the value of the expression to a string.
             */
            String tickLabel = String.valueOf(i == 0 ? 12 : i);
            Label label = new Label(tickLabel);
            label.setTextFill(Color.WHITE);
            //label.setFont(Font.font("Helvetica"));
            label.setFont(Font.font("Helvetica", FontWeight.BLACK, 16));
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
        hourHand.setEndY(centerY - clockRadius * 0.5);
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
        secondHand.setEndY(centerY - clockRadius * 0.9);
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
        digitimeLabel.setFont(Font.font("Helvetica", FontWeight.BLACK, 20));
        digitimeLabel.setTextFill(Color.BLACK);

        pane.getChildren().add(digitimeLabel);

        // Create animation timer to update clock hands and time label
        /**this line of code is creating a new AnimationTimer object that can be used to create animations
         * in the JavaFX application by overriding the handle method and providing the logic for the animation.
         * The AnimationTimer class is an abstract class, so it can not be instantiated directly. Instead,
         * this code creates an anonymous inner class that extends the AnimationTimer class and overrides its
         * abstract method handle(long now). This method will be called repeatedly by the JavaFX runtime,
         * providing the current timestamp (in nanoseconds) as an argument.
         */
        AnimationTimer timer = new AnimationTimer() {
            /**The handle(long now) method takes a single argument, a long value named "now" which represents the
             * current timestamp in nanoseconds. The timestamp is generated by the operating system's
             * high-resolution timer, and it is passed to the handle method each time it is called.
             */
            @Override

            public void handle(long now) {
                //the actual time
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                updateClockHands(hour, minute, second);
                //digitimeLabel.setText(String.format("%02d:%02d:%02d", hour, minute, second));
            }
        };
        /** The timer.start() method call is used to start the AnimationTimer object.
         * Once the AnimationTimer object is started, the handle(long now) method will
         * be called repeatedly by the JavaFX runtime
         */
        timer.start();

    }

    private void updateClockHands(double hour, double minute, double second) {

        //The Angles where the hands need to be pointing to
        double hourAngle = (hour % 12 + minute / 60.0) * 30;
        double minuteAngle = (minute + second / 60.0) * 6;
        double secondAngle = second * 6;

        //Rote hands according to the angle in animation time
        ((Rotate) hourHand.getTransforms().get(0)).setAngle(hourAngle);
        ((Rotate) minuteHand.getTransforms().get(0)).setAngle(minuteAngle);
        ((Rotate) secondHand.getTransforms().get(0)).setAngle(secondAngle);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
