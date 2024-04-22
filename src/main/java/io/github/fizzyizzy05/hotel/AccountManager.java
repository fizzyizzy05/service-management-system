// Small and simple class to hold current account information. SQL queries and such are handled in application code.
package io.github.fizzyizzy05.hotel;

public class AccountManager {
    private String email;
    private String firstName;
    private String lastName;
    private int accountID;
    
    public AccountManager() {}

    public void login(String email, String firstName, String lastName, int accountID) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
    }
}
