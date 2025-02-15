module rubikscube {
    requires javafx.controls;
    requires javafx.fxml;

    opens rubikscube to javafx.fxml;
    exports rubikscube;
}
