package com.example.twentyonedays;

public class HabitModel {
    String HabitName;
    String HabitType;
    String HabitFreq;
    String HabitNum;

    String HabitDesc;
    String Habitimg;

    String timestamp;

    String uid;


    public HabitModel(String habitName, String habitType, String habitFreq, String habitNum) {
        HabitName = habitName;
        HabitType = habitType;
        HabitFreq = habitFreq;
        HabitNum = habitNum;
    }

    public HabitModel(String habitName, String habitDesc, String habitimg, String timeStamp,String habitType, String habitFreq, String UID) {
        HabitName = habitName;
        HabitDesc = habitDesc;
        Habitimg = habitimg;
        HabitType = habitType;
        HabitFreq = habitFreq;
        timestamp = timeStamp;
        uid = UID;
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
}
