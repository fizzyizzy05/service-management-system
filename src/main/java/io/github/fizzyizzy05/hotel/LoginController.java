package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.io.IOException;

public class LoginController {

    // Validate the user
    @FXML
    private void login() throws IOException {
        System.out.println("Logging in!");
    }

    // Change the screen to login
    @FXML
    private void goRegister() throws IOException {
        App.setRoot("register");
    }
}
