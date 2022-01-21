package com.example.myclinic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PatientAccount extends UserAccount implements Serializable {

    boolean isEmployeeAccount;
    Appointment appointment;

    public PatientAccount(String id, String username, String password){
        super.username = username;
        super.password = password;
        super.id = id;
        super.type = "Patient";
    }

    public PatientAccount(String id, String username, String password, Appointment appointment){
        super.username = username;
        super.password = password;
        super.id = id;
        super.type = "Patient";
        this.appointment = appointment;

    }

    public PatientAccount(){

    }

    public String getUsername(){
        return super.getUsername();
    }
    public String getPassword(){
        return super.getPassword();
    }
    public String getId(){
        return super.getId();
    }
    public String getType(){
        return super.getType();
    }
    public Appointment getAppointment(){return appointment;}
    public void setAppointment(Appointment a){
        this.appointment = a;
    }

}
