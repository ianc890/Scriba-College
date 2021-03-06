package com.example.scriba.scribacollege.model;

/**
 * @author Ian Cunningham.
 */

public class User {

    private String firstname, surname, email, username, password, encryptedPassword;

    public User(String firstname, String surname, String email, String username, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
