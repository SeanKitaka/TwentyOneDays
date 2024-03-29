package com.example.twentyonedays;

public class HabitModel {
    String HabitName;
    String HabitType;
    String HabitFreq;
    String HabitNum;
    String HabitDesc;
    String timestamp;



    String uid;


    public HabitModel(String UID,String habitName, String habitType,String habitFreq, String habitDesc, String timeStamp) {
        HabitName = habitName;
        HabitDesc = habitDesc;
        HabitType = habitType;
        HabitFreq = habitFreq;
        timestamp = timeStamp;
        uid = UID;
    }

    public HabitModel(String habitName, String habitType,String habitFreq, String habitDesc, String timeStamp) {
        HabitName = habitName;
        HabitDesc = habitDesc;
        HabitType = habitType;
        HabitFreq = habitFreq;
        timestamp = timeStamp;
    }

    public String getHabitName() {
        return HabitName;
    }

    public String getHabitType() {
        return HabitType;
    }

    public String getHabitFreq() {
        return HabitFreq;
    }

    public String getHabitNum() {
        return HabitNum;
    }

    public String getHabitDesc() {
        return HabitDesc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setHabitNum(String s) {
        HabitNum = s;
    }

    public String getUID() {
        return uid;
    }
}
