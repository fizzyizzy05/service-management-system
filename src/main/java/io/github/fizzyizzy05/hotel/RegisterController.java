package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML private TextField firstNameIn;

    @FXML private void register() throws IOException {
        System.out.println("abc");
        System.out.println(firstNameIn.getText());
    }

    @FXML private void goLogin() throws IOException{
        App.setRoot("login");
    }
}