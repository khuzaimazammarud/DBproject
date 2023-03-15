package com.example.dbproject.model;

public class Event {

    private String eventName;
    private String startDate;
    private String endDate;
    private int eventId;
    private int SocietyId;


    public Event(String eventName, String startDate, String endDate, int eventId, int societyId) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventId = eventId;
        SocietyId = societyId;
    }


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setSocietyId(int societyId) {
        SocietyId = societyId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getEventId() {
        return eventId;
    }

    public int getSocietyId() {
        return SocietyId;
    }
}
