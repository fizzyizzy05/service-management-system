// Small and simple class to hold current account information. SQL queries and such are handled in application code.
package io.github.fizzyizzy05.hotel;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountManager {
    private String email;
    private String firstName;
    private String lastName;
    private int accountID = -1;
    private String password;
    private String phoneNo;
    private boolean staff;
    
    public AccountManager() {}

    public void login(String email, String firstName, String lastName, int accountID, String password, String phoneNo, boolean staff) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
        this.password = password;
        this.phoneNo = phoneNo;
        this.staff = staff;
    }

    public String[] getNames() {
        String[] names = {this.firstName, this.lastName};
        return names;
    }

    public String getEmail() {
        return this.email;
    }

    public int getID() {
        return this.accountID;
    }

    public void logout() {
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.accountID = -1;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void updateInfo() throws SQLException {
        Connection dbConnection = App.getConnection();
        Statement stmt = dbConnection.createStatement();
        ResultSet accountDetails = stmt.executeQuery("SELECT ID, firstName, lastName, email, password, phoneNo FROM Users WHERE ID='" + this.accountID + "';");
        this.email = accountDetails.getString("email");
        this.password = accountDetails.getString("password");
        this.firstName = accountDetails.getString("firstName");
        this.lastName = accountDetails.getString("lastName");
        this.accountID = accountDetails.getInt("ID");
        this.phoneNo = accountDetails.getString("phoneNo");
        stmt.close();
        dbConnection.close();
    }
}
