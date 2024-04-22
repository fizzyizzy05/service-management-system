package io.github.fizzyizzy05.hotel;

import javafx.fxml.FXML;
import java.sql.Statement;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.PasswordField;

public class changeDefaultPassController {
    @FXML PasswordField newPassIn;
    @FXML PasswordField repeatPassIn;

    public void changePassword() throws IOException, SQLException {
        Statement stmt = App.getConnection().createStatement();
        stmt.executeQuery("UPDATE Users SET password = '" + newPassIn.getText() + "' WHERE email = '" + App.getAccountManager().getEmail() + "';");
        stmt.close();
        App.setRoot("admin");
    }
}
