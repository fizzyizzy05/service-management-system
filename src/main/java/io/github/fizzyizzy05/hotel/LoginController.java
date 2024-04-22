package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    // Import the widgets used in the FXML file
    @FXML private TextField emailIn;
    @FXML private PasswordField passIn;

    // Validate the user
    @FXML
    private void login() throws IOException, SQLException {
        Connection dbConnection = App.getConnection();
        ResultSet accDetails = dbConnection.createStatement().executeQuery("SELECT password FROM Users WHERE email = '" + emailIn.getText() + "';");
        if (passIn.getText().equals(accDetails.getString("password"))) {
            System.out.println("Logging in");
        } else if (accDetails.getString("password") == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setContentText("No account with this username was found. Please try again, or register a new account.");
            alert.showAndWait();
        } 
    }

    // Change the screen to register
    @FXML
    private void goRegister() throws IOException {
        App.setRoot("register");
    }
}
