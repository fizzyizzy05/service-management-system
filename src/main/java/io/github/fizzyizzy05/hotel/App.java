package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

// The App Class handles opening the booking system window. Most application code occurs in the controller files.
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 800, 640);
        stage.setTitle("Service Management System");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // This is the main functionality that starts the booking system.
    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        Connection dbConnection = getConnection();
        // Using the "IF NOT E"
        String[] sql = {"CREATE TABLE IF NOT EXISTS Users " + 
                               "(ID INT NOT NULL, " + 
                               "firstName TEXT NOT NULL, " + 
                               "lastName TEXT NOT NULL, " + 
                               "password TEXT NOT NULL, " +
                               "email TEXT NOT NULL, " + 
                               "phoneNo INT(11), " +
                               "ROLE INT NOT NULL," + 
                               "PRIMARY KEY (ID));",
        };
        stmt = dbConnection.createStatement();
        for (String s : sql) {
            stmt.executeUpdate(s);
        }
        stmt.close();
        launch();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:hotel-database.db");
    }
}