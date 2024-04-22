package io.github.fizzyizzy05.hotel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.fxml.FXML;

public class AdminController {
    private String loggedInUser;
    
    @FXML public static void refresh() throws IOException, SQLException {
        Connection dbConnection = App.getConnection();
    }

}
