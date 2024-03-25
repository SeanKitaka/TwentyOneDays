package com.example.twentyonedays;

public class User {

    String uid;
    String firstName;
    String[] friends;


    public User(String uid, String firstName) {
        this.uid = uid;
        this.firstName = firstName;
    }


    //region getters and setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
    //endregion
}
