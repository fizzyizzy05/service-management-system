package io.github.fizzyizzy05.hotel;

import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings({"rawtypes", "unchecked"})
public class NewAppointmentController {
    @FXML ChoiceBox serviceBox;
    @FXML TextField titleIn;
    @FXML TextArea descIn;
    @FXML DatePicker dateIn;
    @FXML TextField hourIn;
    @FXML TextField minIn;

    private int maxID;
    public void initialize() throws IOException, SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        ResultSet services = stmt.executeQuery("SELECT * FROM Services;");
        while (services.next()) {
            System.out.print(services.getInt("id") + ": " + services.getString("name") + " " + Widgets.formatPrice(services.getInt("hourlyRate")));
            serviceBox.getItems().add(services.getInt("id") + ": " + services.getString("name") + " " + Widgets.formatPrice(services.getInt("hourlyRate")));
            serviceBox.getSelectionModel().select(0);
        }
        stmt.close();
        dbConnection.close();
    }

    @FXML public void makeAppointment() throws IOException, SQLException {
        if (titleIn.getText().equals("") || dateIn.getValue().equals(null) || hourIn.getText().equals("") || minIn.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in the form properly");
            alert.showAndWait();
        } else {
            String selected = (String) serviceBox.getSelectionModel().getSelectedItem();
            int serviceID = Integer.parseInt(selected.substring(0, selected.indexOf(":")));

            Connection dbConnection = App.getConnection();
            Statement stmt = dbConnection.createStatement();
            ResultSet appointments = stmt.executeQuery("SELECT * FROM Appointments;");
            while (appointments.next()) {
                maxID = appointments.getInt("ID") + 1;
            }
            stmt.executeUpdate("INSERT INTO Appointments" + 
                                "(ID, title, desc, service, time, customer) VALUES "
                                + "('" + maxID + "', '"
                                + titleIn.getText() + "', '"
                                + descIn.getText() + "', '"
                                + serviceID + "', '"
                                + dateIn.getValue() + " " + hourIn.getText() + ":" + minIn.getText() + "', '"
                                + App.getAccountManager().getID() + "');"
            );
            stmt.close();
            dbConnection.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Complete");
            alert.setContentText("Appointment has been made. Standby for updates.");
            alert.showAndWait();
            App.setRoot("customer");
        }
    }
}
