package com.harry.firestoreapp;

public class Friend {

    String name;
    String email;


    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }
    // Firebase always need empty constructor


    public Friend() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


