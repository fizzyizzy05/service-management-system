module io.github.fizzyizzy05.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens io.github.fizzyizzy05.hotel to javafx.fxml;
    exports io.github.fizzyizzy05.hotel;
}
