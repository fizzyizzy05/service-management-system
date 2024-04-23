package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class AdminPasswordChangeController {
    @FXML PasswordField newPassIn;
    @FXML PasswordField repeatPassIn;

    public void changePassword() throws IOException, SQLException {
        if (newPassIn.getText().equals(repeatPassIn.getText()) && !(newPassIn.getText().equals("admin") || newPassIn.getText().equals(""))) {
            Connection dbConnection = App.getConnection();
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate("UPDATE Users SET password = '" + newPassIn.getText() + "' WHERE email = '" + App.getAccountManager().getEmail() + "';");
            stmt.close();
            dbConnection.close();
            App.getAccountManager().updateInfo();
            App.setRoot("admin");
        } else if (newPassIn.getText().equals("admin") || newPassIn.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("This password is not secure enough. Please try a different one.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("Passwords do not match. Please try again.");
            alert.showAndWait();
        }
    }

    public void logout() throws IOException {
        App.setRoot("login");
    }
}
