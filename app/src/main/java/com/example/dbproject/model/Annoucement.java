package com.example.dbproject.model;

public class Annoucement {

    private int AnnoucementId;
    private String AnnoucementTitle;
    private String AnnoucementStarDate;
    private String AnnoucementEndDate;

    private String Message;

    public String getAnnoucementTitle() {
        return AnnoucementTitle;
    }

    public String getAnnoucementStarDate() {
        return AnnoucementStarDate;
    }

    public String getAnnoucementEndDate() {
        return AnnoucementEndDate;
    }

    public Annoucement(String annoucementTitle, String annoucementStarDate, String annoucementEndDate, String message) {
        AnnoucementTitle = annoucementTitle;
        AnnoucementStarDate = annoucementStarDate;
        AnnoucementEndDate = annoucementEndDate;
        Message = message;
    }

    public Annoucement(int annoucementId, String message) {
        AnnoucementId = annoucementId;
        Message = message;
    }

    public Annoucement(int annoucementId, String annoucementTitle, String annoucementStarDate, String message) {
        AnnoucementId = annoucementId;
        AnnoucementTitle = annoucementTitle;
        AnnoucementStarDate = annoucementStarDate;
        Message = message;
    }

    public Annoucement(String message) {
        Message = message;
    }

    public int getAnnoucementId() {
        return AnnoucementId;
    }

    public String getMessage() {
        return Message;
    }

}