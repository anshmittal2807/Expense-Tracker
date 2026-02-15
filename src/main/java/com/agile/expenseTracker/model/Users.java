package com.agile.expenseTracker.model;

import jakarta.persistence.*;

@Entity
public class Users {

    @Column(unique = true)
    private String userName;

    private String password;

    @Column(unique = true)
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in DB
    private Integer userId;

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
