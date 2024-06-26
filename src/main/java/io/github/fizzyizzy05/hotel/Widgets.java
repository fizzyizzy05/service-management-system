package io.github.fizzyizzy05.hotel;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

@SuppressWarnings("exports")
public class Widgets {
    public static HBox serviceWidget(int id, String name, int hourlyRate) {
        // Content
        HBox widget = new HBox();
        Label idLbl = new Label(Integer.toString(id));
        Label nameLbl = new Label(name);
        Label rateLbl = new Label(formatPrice(hourlyRate));
        // Styling
        widget.setPadding(new Insets(5, 10, 5, 10));
        nameLbl.setPadding(new Insets(0, 5, 0, 5));
        widget.getChildren().addAll(idLbl, nameLbl, rateLbl);
        // Return this custom widget
        return widget;
    }

    public static HBox appointmentWidget(int id, String title, String desc, String service, String time, int Customer) throws SQLException {
        HBox widget = new HBox();
        Label idLbl = new Label(Integer.toString(id));
        Label nameLbl = new Label(title);
        Label serviceLbl = new Label(service);
        Label customerLbl = new Label(getCustomer(Customer));
        // Styling
        widget.setPadding(new Insets(5, 10, 5, 10));
        nameLbl.setPadding(new Insets(0, 5, 0, 5));
        serviceLbl.setPadding(new Insets(0, 5, 0, 0));
        widget.getChildren().addAll(idLbl, nameLbl, serviceLbl, customerLbl);
        return widget;
    }

    // Function to get a formatted rate in pence/pounds
    public static String formatPrice(int rate) {
        String outputRate;
        Double doubleRate = Double.valueOf(rate) / 100;
        if (rate >= 100) {
            outputRate = "£" + String.format("%.2f", doubleRate) + " per hour";
        } else if (rate < 100) {
            outputRate = String.format("%.2f", doubleRate) + "p per hour";
        } else {
            outputRate = "Free";
        }
        return outputRate;
    }

    public static String getService(int id) throws SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        ResultSet services = stmt.executeQuery("SELECT name FROM Services WHERE id='" + id + "';");
        String service = services.getString("name");
        stmt.close();
        dbConnection.close();
        return service;
    }

    public static String getCustomer(int id) throws SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        ResultSet users = stmt.executeQuery("SELECT firstName, lastName FROM Users WHERE id='" + id + "';");
        String name = users.getString("firstName") + " " + users.getString("lastName");
        stmt.close();
        dbConnection.close();
        return name;
    }
}