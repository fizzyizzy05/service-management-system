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
        Statement stmt = dbConnection.createStatement();
        ResultSet accDetails = stmt.executeQuery("SELECT password, firstName, lastName, ID FROM Users WHERE email = '" + emailIn.getText() + "';");
        if (passIn.getText().equals(accDetails.getString("password"))) {
            App.getAccountManager().login(emailIn.getText(), accDetails.getString("firstName"), accDetails.getString("lastName"), accDetails.getInt("ID"), accDetails.getString("password"));
            dbConnection.close();
            App.setRoot("admin");
        } else if (accDetails.getString("password") == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setContentText("No account with this username was found. Please try again, or register a new account.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setContentText("This password is incorrect. Please try again.");
            alert.showAndWait();
        }
    }

    // Change the screen to register
    @FXML
    private void goRegister() throws IOException {
        App.setRoot("register");
    }
}
