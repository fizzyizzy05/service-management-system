package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class DeleteAccountController {
    @FXML PasswordField passIn;
    @FXML PasswordField passRepeat;
    @FXML Label subtitle;

    AccountManager accountManager = App.getAccountManager();

    @FXML public void initialize() throws IOException {
        subtitle.setText(String.format("Delete all details for the account attached to email address %s", accountManager.getEmail()));
    }

    @FXML public void delete() throws IOException, SQLException {
        if (passIn.getText().equals(passRepeat.getText())) {
            if (passIn.getText().equals(accountManager.getPassword())) {
                App.setRoot("login");
                Connection dbConnection = App.getConnection();
                Statement stmt = dbConnection.createStatement();
                stmt.executeUpdate("DELETE FROM Users WHERE ID ='" + App.getAccountManager().getID() + "';");
                App.getAccountManager().logout();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incorrect password");
                alert.setContentText("You entered the wrong password.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Password error");
            alert.setContentText("Passwords are not the same.");
            alert.showAndWait();
        }
    }

    public void goBack() throws IOException {
        if (App.getAccountManager().isStaff()) {
            App.setRoot("admin"); 
        } else {
            App.setRoot("customer");
        }
    }
}
