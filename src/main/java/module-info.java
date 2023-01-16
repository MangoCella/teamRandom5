module team.random5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens team.random5 to javafx.fxml;
    exports team.random5;
}