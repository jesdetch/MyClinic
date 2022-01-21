package com.example.myclinic;

import java.io.Serializable;

public class Appointment implements Serializable {

    private int day;
    private int dayOfWeek;
    private int startHour;
    private int startMinute;
    private int month;

    Appointment(int DayOfWeek, int month, int Day, int StartHour, int StartMinute){

        this.day = Day;
        this.month = month;
        this.dayOfWeek = DayOfWeek;
        this.startHour = StartHour;
        this.startMinute = StartMinute;

    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getDay() {
        return day;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }


}
