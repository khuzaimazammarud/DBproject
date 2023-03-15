package com.example.dbproject.model;

import androidx.dynamicanimation.animation.SpringAnimation;

public class userViewSociety {

    private String societyname;
    private String eventname;
    private String startdate;
    private String enddate;
    private int societyid;

    public userViewSociety(String societyname, String eventname, String startdate, String enddate, int societyid) {
        this.societyname = societyname;
        this.eventname = eventname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.societyid = societyid;
    }

    public userViewSociety(String societyname, String eventname, String startdate, String enddate) {
        this.societyname = societyname;
        this.eventname = eventname;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getSocietyid() {
        return societyid;
    }

    public String getSocietyname() {
        return societyname;
    }

    public String getEventname() {
        return eventname;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }
}
