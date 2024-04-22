// Small and simple class to hold current account information. SQL queries and such are handled in application code.
package io.github.fizzyizzy05.hotel;

public class AccountManager {
    private String email;
    private String firstName;
    private String lastName;
    private int accountID = -1;
    private String password;
    
    public AccountManager() {}

    public void login(String email, String firstName, String lastName, int accountID, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
        this.password = password;
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
}
