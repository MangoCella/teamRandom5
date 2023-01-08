module team.random.teamrandom {
    requires javafx.controls;
    requires javafx.fxml;


    opens team.random.teamrandom to javafx.fxml;
    exports team.random.teamrandom;
}