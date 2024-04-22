package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.PasswordField;

public class changeDefaultPassController {
    @FXML PasswordField newPassIn;
    @FXML PasswordField repeatPassIn;

    public void changePassword() throws IOException, SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        stmt.executeUpdate("UPDATE Users SET password = '" + newPassIn.getText() + "' WHERE email = '" + App.getAccountManager().getEmail() + "';");
        stmt.close();
        dbConnection.close();
        App.getAccountManager().updateInfo();
        App.setRoot("admin");
    }
}
