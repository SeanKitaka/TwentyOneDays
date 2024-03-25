package com.example.twentyonedays;

public class HabitModel {
    String HabitName;
    String HabitType;
    String HabitFreq;
    String HabitNum;


    public HabitModel(String habitName, String habitType, String habitFreq, String habitNum) {
        HabitName = habitName;
        HabitType = habitType;
        HabitFreq = habitFreq;
        HabitNum = habitNum;
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
