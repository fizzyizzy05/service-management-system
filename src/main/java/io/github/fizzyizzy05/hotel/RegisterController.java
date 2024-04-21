package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.io.IOException;
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

    // Create a new user account
    @FXML private void register() throws IOException {
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
        }
    }

    // Go back to the login screen
    @FXML private void goLogin() throws IOException{
        App.setRoot("login");
    }
}