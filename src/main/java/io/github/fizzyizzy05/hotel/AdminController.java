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
        Connection dbConnection = App.getConnection();
        Statement usrStmt = dbConnection.createStatement();
        ResultSet usrResults = usrStmt.executeQuery("SELECT firstName, lastName, email FROM Users WHERE email='" + loggedInUser + "';");
        nameLabel.setText(String.format("%s %s (%s)", usrResults.getString("firstName"), usrResults.getString("lastName"),usrResults.getString("email")));
    }

}
