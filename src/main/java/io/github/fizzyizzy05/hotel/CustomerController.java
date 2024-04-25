package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class CustomerController {
    String loggedInUser;
    @FXML Label nameLabel;
    AccountManager accountManager = App.getAccountManager();

    @FXML TextField firstNameIn;
    @FXML TextField lastNameIn;
    @FXML TextField phoneIn;
    @FXML TextField emailIn;

    @FXML Accordion accordion;
    @FXML TitledPane appointmentsPage;

    @FXML public void refresh() throws IOException {
        nameLabel.setText(String.format("%s %s (%s)", accountManager.getNames()[0], accountManager.getNames()[1], accountManager.getEmail()));
        firstNameIn.setText(accountManager.getNames()[0]);
        lastNameIn.setText(accountManager.getNames()[1]);
        emailIn.setText(accountManager.getEmail());
        phoneIn.setText(accountManager.getPhoneNo());
    }

    @FXML public void logout() throws IOException {
        accountManager.logout();
        App.setRoot("login");
    }

    public void initialize() throws IOException {
        refresh();
        accordion.setExpandedPane(appointmentsPage);
    }

    @FXML public void updateInfo() throws IOException, SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        stmt.executeUpdate("UPDATE Users SET firstName = '" + firstNameIn.getText() + "' WHERE ID = '" + accountManager.getID() + "';");
        stmt.executeUpdate("UPDATE Users SET lastName = '" + lastNameIn.getText() + "' WHERE ID = '" + accountManager.getID() + "';");
        stmt.executeUpdate("UPDATE Users SET email = '" + emailIn.getText() + "' WHERE ID = '" + accountManager.getID() + "';");
        stmt.executeUpdate("UPDATE Users SET phoneNo = '" + phoneIn.getText() + "' WHERE ID = '" + accountManager.getID() + "';");
        stmt.close();
        dbConnection.close();
        accountManager.updateInfo();
        refresh();
    }

    @FXML public void deleteAccount() throws IOException {
        App.setRoot("delete-account");
    }

    @FXML public void newAppointment() throws IOException {
        App.setRoot("new-appointment");
    }
}
