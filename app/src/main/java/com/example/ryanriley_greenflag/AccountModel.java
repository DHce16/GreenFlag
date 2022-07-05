package com.example.ryanriley_greenflag;

import androidx.annotation.NonNull;

public class AccountModel {

    private final String email;
    private final String password;

    public AccountModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "com.example.ryanriley_greenflag.AccountModel{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
