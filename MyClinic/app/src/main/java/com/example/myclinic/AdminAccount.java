package com.example.myclinic;

import java.util.ArrayList;

class AdminAccount extends UserAccount {

    public AdminAccount(){
        super.password = "5T5ptQ";
        super.username = "admin" ;
        validAccounts.add(this);
        this.id = MainActivity.accountDatabase.push().getKey();
        MainActivity.accountDatabase.child(id).setValue(this);
    }
    private ArrayList<Service> offeredServices = new ArrayList<Service>();

    private static ArrayList<UserAccount> validAccounts = new ArrayList<UserAccount>();

    public static void addAccount(UserAccount account){
        validAccounts.add(account);
    }

    public static UserAccount getAccount(int index){
        return validAccounts.get(index);
    }

    public static int size(){
        return validAccounts.size();
    }

    public static void removeAccount(UserAccount account){
        validAccounts.remove(account);
    }


    public void addService(Service s){
        offeredServices.add(s);
    }

    public void removeService(Service s){
        offeredServices.remove(s);
    }
    public ArrayList<Service> getServices(){
        return offeredServices;
    }



}
