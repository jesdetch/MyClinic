package com.example.myclinic;

import android.app.Service;
import android.os.Parcelable;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

class UserAccount implements Serializable {
    protected String password, username, id, type;

    //added an arraylist to hold listOfservices so that adminAccount & employeeAccount can add an modify
    //protected ArrayList<Service> servicesList = new ArrayList<Service>();

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getId() {
        return id;
    }
    public String getType(){
        return type;
    }
}
