package com.example.myclinic;

import java.io.Serializable;

class WorkingHours implements Serializable {

    protected String day;
    protected int startHour;
    protected int endHour;
    protected int startMinute;
    protected int endMinute;
    protected String id;

    public WorkingHours (String id, String day, int startHour, int startMinute, int endHour, int endMinute){
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.id  = id;
    }
    public WorkingHours ( String day, int startHour, int startMinute, int endHour, int endMinute){
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;

    }

    public WorkingHours(){

    }


    public void setId(String id) {
        this.id = id;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getStartHour() {
        return startHour;
    }

    public String getDay() {
        return day;
    }

    public String getId() {
        return id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public String toString(int j){
        String d = Integer.toString(j);
        return d;
    }

}
