package com.example.dbproject.model;

public class Society {

    private String societyName;
    private int societyid;


    public Society(String societyName, int societyid) {
        this.societyName = societyName;
        this.societyid = societyid;
    }



    public String getSocietyName() {
        return societyName;
    }

    public int getSocietyid() {
        return societyid;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public void setSocietyid(int societyid) {
        this.societyid = societyid;
    }
}
