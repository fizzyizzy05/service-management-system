module io.github.fizzyizzy05.hotel {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.fizzyizzy05.hotel to javafx.fxml;
    exports io.github.fizzyizzy05.hotel;
}
