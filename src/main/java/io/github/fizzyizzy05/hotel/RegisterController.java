package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class RegisterController {
    // Import the widgets used in the fxml file
    @FXML private TextField firstNameIn;
    @FXML private TextField lastNameIn;
    @FXML private TextField emailIn;
    @FXML private TextField emailConfirm;
    @FXML private TextField phoneIn;
    @FXML private TextField phoneConfirm;
    @FXML private PasswordField passIn;
    @FXML private PasswordField passConfirm;
    @FXML private CheckBox agreeTC;

    public int maxID;

    // Create a new user account
    @FXML private void register() throws IOException, SQLException {
        // Long conditional used to ensure the form is properly filled out
        if(firstNameIn.getText().equals("") || lastNameIn.getText().equals("") || emailIn.getText().equals("") || passIn.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("Please fill out all non-optional form fields.");
            alert.showAndWait();
        } else if (!emailIn.getText().equals(emailConfirm.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("Please double check you have entered your email address correctly.");
            alert.showAndWait();
        }
        else if (!passIn.getText().equals(passConfirm.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("Please double check you have entered your password correctly.");
            alert.showAndWait();
        }
        else if (!phoneIn.getText().equals(phoneConfirm.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("Please double check you have entered your phone number correctly.");
            alert.showAndWait();
        } else if (!agreeTC.isSelected()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form error");
            alert.setContentText("You need to agree to the terms and conditions");
            alert.showAndWait();
        } else {
            try {
                Connection dbConnection = App.getConnection();
                Statement stmt = dbConnection.createStatement();
                ResultSet accounts = stmt.executeQuery("SELECT id FROM Users;");
                while (accounts.next()) {
                    maxID = accounts.getInt("id") + 1;
                }
                stmt.executeUpdate("INSERT INTO Users " +
                                    "(id, firstName, lastName, password, email, phoneNo, staff) " +
                                    "VALUES ('" + maxID + "', " +
                                    "'" + firstNameIn.getText() + "', " +
                                    "'" + lastNameIn.getText() + "', " +
                                    "'" + passIn.getText() + "', " +
                                    "'" + emailIn.getText() + "', " +
                                    "'" + phoneIn.getText() + "', " +
                                    "'" + false + "'', " +
                                    "');"
                );
                stmt.close();
                dbConnection.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Account created");
                alert.setContentText("Account has been successfully created");
                alert.showAndWait();
                App.getAccountManager().login(emailIn.getText(), firstNameIn.getText(), lastNameIn.getText(), maxID, passIn.getText(), phoneIn.getText(), false);
            } catch (Exception SQLException) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Database error");
                alert.setContentText("There was an error with updating the database. Please contact the system administrator.");
                alert.showAndWait();
            }
        }
    }

    // Go back to the login screen
    @FXML private void goLogin() throws IOException{
        App.setRoot("login");
    }
}