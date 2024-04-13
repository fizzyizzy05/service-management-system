package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.io.IOException;

public class LoginController {

    @FXML
    private void login() throws IOException {
        System.out.println("Logging in!");
    }

    @FXML
    private void goRegister() throws IOException {
        App.setRoot("register");
    }
}
