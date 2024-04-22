package io.github.fizzyizzy05.hotel;

import java.sql.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

// The App Class handles opening the booking system window. Most application code occurs in the controller files for each page.
public class App extends Application {

    private static Scene scene;
    private static AccountManager accountManager = new AccountManager();

    @Override
    // Declare a function to open the window, used in App.main()
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 800, 640);
        stage.setTitle("Service Management System");
        stage.setScene(scene);
        stage.show();
    }

    // Use a function so that we can set the window root from anywhere
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // This is the main functionality that starts the system.
    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        Connection dbConnection = getConnection();
        // Using the "IF NOT EXISTS" command in SQL to create a new table if it doesn't exist, allowing for seamless initialisation of the database. 
        String[] sql = {"CREATE TABLE IF NOT EXISTS Users " + 
                                "(ID INT NOT NULL, " + 
                                "firstName TEXT NOT NULL, " + 
                                "lastName TEXT NOT NULL, " + 
                                "password TEXT NOT NULL, " +
                                "email TEXT NOT NULL, " + 
                                "phoneNo INT(11), " +
                                "staff BOOL NOT NULL," + 
                                "PRIMARY KEY (ID));",
                        "INSERT OR IGNORE INTO Users (ID, firstName, lastName, password, email, phoneNo, staff) " + 
                                "VALUES ('0', 'First Name', 'Last Name', 'admin', 'admin@localhost', '07123456789', 'true');",
                        "CREATE TABLE IF NOT EXISTS Services " + 
                                "(ID INT NOT NULL, " + 
                                "name TEXT NOT NULL, " +
                                "hourlyRate INT NOT NULL, " + 
                                "PRIMARY KEY (ID));",
                        "CREATE TABLE IF NOT EXISTS Appointments " + 
                                "(ID INT NOT NULL, " +
                                "title TEXT NOT NULL, " + 
                                "desc TEXT, " + 
                                "service INT NOT NULL, " +
                                "time DATETIME NOT NULL, " + 
                                "customer INT NOT NULL, " + 
                                "FOREIGN KEY (customer) REFERENCES Users(ID) " +
                                "FOREIGN KEY (service) REFERENCES Services(ID) " +
                                "PRIMARY KEY (ID));"
        };
        stmt = dbConnection.createStatement();
        for (String s : sql) {
            stmt.executeUpdate(s);
        }
        stmt.close();
        launch();
    }

    // We use a common function for connecting to the database in order to ensure that we always use the same database.
    @SuppressWarnings("exports")
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:hotel-database.db");
    }

    public static AccountManager getAccountManager() {
        return accountManager;
    }
}