package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Label;

public class AdminController {
    String loggedInUser;
    @FXML Label nameLabel;
    
    @FXML public void refresh() throws IOException, SQLException {
        AccountManager accountManager = App.getAccountManager();
        nameLabel.setText(String.format("%s %s (%s)", accountManager.getNames()[0], accountManager.getNames()[1], accountManager.getEmail()));
    }

}
