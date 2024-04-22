package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.Label;

public class AdminController {
    String loggedInUser;
    @FXML Label nameLabel;
    AccountManager accountManager = App.getAccountManager();

    @FXML public void refresh() throws IOException {
        if (accountManager.getEmail().equals("admin@localhost") && accountManager.getPassword().equals("admin")) {
            App.setRoot("admin-password-change");
        }
        nameLabel.setText(String.format("%s %s (%s)", accountManager.getNames()[0], accountManager.getNames()[1], accountManager.getEmail()));
    }

    @FXML public void logout() throws IOException {
        accountManager.logout();
        App.setRoot("login");
    }

}
