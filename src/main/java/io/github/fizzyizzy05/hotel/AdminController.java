package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class AdminController {
    // Account label
    AccountManager accountManager = App.getAccountManager();
    @FXML Label nameLabel;

    // Main accordion
    @FXML Accordion accordion;
    @FXML TitledPane appointmentsPage;

    // Manage account
    @FXML TextField firstNameIn;
    @FXML TextField lastNameIn;
    @FXML TextField phoneIn;
    @FXML TextField emailIn;
    @FXML Button delAccountBtn;

    // Add service
    @FXML TextField serviceNameIn;
    @FXML TextField hourlyRateIn;

    // Current Services & Appointments
    ArrayList<Service> services;
    @FXML VBox servicesList;
    @FXML TextField idSelect;
    @FXML VBox appointmentsList;

    @FXML public void refresh() throws IOException, SQLException {
        services = new ArrayList<Service>();
        servicesList.getChildren().clear();
        nameLabel.setText(String.format("%s %s (%s)", accountManager.getNames()[0], accountManager.getNames()[1], accountManager.getEmail()));
        firstNameIn.setText(accountManager.getNames()[0]);
        lastNameIn.setText(accountManager.getNames()[1]);
        emailIn.setText(accountManager.getEmail());
        phoneIn.setText(accountManager.getPhoneNo());

        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        ResultSet serviceRs = stmt.executeQuery("SELECT * FROM Services;");
        while (serviceRs.next()) {
            services.add(new Service(serviceRs.getInt("id"), serviceRs.getString("name"), serviceRs.getInt("hourlyRate")));
            servicesList.getChildren().add(Widgets.serviceWidget(serviceRs.getInt("id"), serviceRs.getString("name"), serviceRs.getInt("hourlyRate")));
        }

        ResultSet appointments = stmt.executeQuery("SELECT * FROM Appointments;");
        while (appointments.next()) {
            appointmentsList.getChildren().add(Widgets.appointmentWidget(appointments.getInt("id"), appointments.getString("title"), appointments.getString("desc"), Widgets.getService(appointments.getInt("id")), appointments.getString("time"), appointments.getInt("customer")));
        }

        stmt.close();
        dbConnection.close();
    }

    @FXML public void logout() throws IOException {
        accountManager.logout();
        App.setRoot("login");
    }

    public void initialize() throws IOException, SQLException {
        refresh();
        if (accountManager.getEmail().equals("admin@localhost")) {
            emailIn.setEditable(false);
            emailIn.setOpacity(0.5);
            delAccountBtn.setDisable(true);
            delAccountBtn.setOpacity(0.5);
        }
        accordion.setExpandedPane(appointmentsPage);
    }

    @FXML public void updateInfo() throws IOException, SQLException {
        try {
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
        } catch (Exception SQLException) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setContentText("There was an error with updating the database. Please contact the system administrator.");
            alert.showAndWait();
        }
    }

    @FXML public void addService() throws IOException, SQLException {
        if (serviceNameIn.getText().equals("") || hourlyRateIn.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in the service and hourly rate");
            alert.showAndWait();
        } else {
            int id;
            if (services.size() > 0) {
                id = services.get(services.size() - 1).getID() + 1;
            } else {
                id = 1;
            }
            Connection dbConnection = App.getConnection();
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate("INSERT INTO Services (id, name, hourlyRate) " +
                                "VALUES ('" + id + "', '" +
                                serviceNameIn.getText() + "', " +
                                "'" + Integer.parseInt(hourlyRateIn.getText()) + "');"
            );
            stmt.close();
            dbConnection.close();
            refresh();
        }
    }
        
    @FXML public void deleteService() throws IOException, SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        stmt.executeUpdate("DELETE FROM Services WHERE id='" + idSelect.getText() + "';");
        stmt.close();
        dbConnection.close();
        refresh();
    }
}
