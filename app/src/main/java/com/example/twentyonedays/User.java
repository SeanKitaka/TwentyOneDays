package com.example.twentyonedays;

import android.net.Uri;

public class User {

    String uid, email, firstName;
    String[] friends;



    public User(String uid, String email, String firstName) {
        this.uid = uid;
        this.email = email;
        this.firstName = firstName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }
}
