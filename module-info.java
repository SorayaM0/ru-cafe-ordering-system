module project4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml;
    opens Project4 to javafx.fxml;

    exports Project4;
    exports model;
    exports controller;
}
