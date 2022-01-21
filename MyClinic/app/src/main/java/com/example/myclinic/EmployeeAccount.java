package com.example.myclinic;



import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

class EmployeeAccount extends UserAccount implements Serializable {


    String clinicName;
    String phoneNumber;
    String address;
    String insuranceType;
    String paymentMethod;
    Double waitTime;
    //public static List<Service> serviceList;

    List<Service> services = new ArrayList<Service>();
    List<WorkingHours> workingHours = new ArrayList<WorkingHours>();

    //public static DatabaseReference serviceList;

    public EmployeeAccount(String id, String username, String password){
        super.username = username;
        super.password = password;
        super.id = id;
        super.type = "Employee";
        clinicName = "";
        phoneNumber ="";
        address = "";
        insuranceType = "";
        paymentMethod = "";
        waitTime = 0.0;


        services = new ArrayList<>();
        
        workingHours = workingHours;

    }

    public EmployeeAccount(String id, String username, String password, String clinicName, String phoneNumber, String address, String insuranceType, String paymentMethod){
        super.username = username;
        super.password = password;
        super.id = id;
        super.type = "Employee";
        this.clinicName = clinicName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.insuranceType = insuranceType;
        this.paymentMethod = paymentMethod;


    }
    public EmployeeAccount(){

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
    public void addService(Service service){
       services.add(service);
    }
    public void removeService(Service service){
        services.remove(service);
    }
    public void setClinicName(String clinicName){
        this.clinicName = clinicName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setAdress(String address){
       this.address = address;
    }

    public void setInsuranceType(String insuranceType){
        this.insuranceType = insuranceType;
    }

    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public String getClinicName(){
        return clinicName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public String getInsuranceType(){
        return insuranceType;
    }

    public String getPaymentMethod(){
        return  paymentMethod;
    }

    public Double getWaitTime(){
        return this.waitTime;
    }

    public void incrementWaitTime(){
        this.waitTime = waitTime + 15.0;
    }
    public void setServices(){


    }


    public void addWorkingHours(WorkingHours h){
        workingHours.add(h);

    }
    public void removeWorkingHour(WorkingHours h){
        workingHours.remove(h);
    }


}
