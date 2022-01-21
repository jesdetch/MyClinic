package com.example.myclinic;

import java.io.Serializable;

public class Service implements Serializable {
    protected String name;
    protected String role;
    protected String id;
    double cost = 0;

    public Service(String id, String name, String role){
        this.name = name;
        this.role = role;
        this.id = id;
    }

    public Service( String name, String role){
        this.name = name;
        this.role = role;

    }

    public Service(){};

    public String getName(){ return this.name;}
    public String getRole(){ return this.role;}
    public void setCost(double c){ this.cost = c; }
    public double getCost(){ return this.cost; }
    public void setName(String n){this.name = n;}
    public void setRole(String r){this.role = r;}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
