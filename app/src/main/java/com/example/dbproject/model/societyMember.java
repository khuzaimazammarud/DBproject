package com.example.dbproject.model;

public class societyMember {

    private String memberName;
    private String memberage;
    private String societyname;
    private String memberemail;
    private String memberregno;
    private int societyid;

    public societyMember(String memberName, String memberage, String societyname, String memberemail, String memberregno) {
        this.memberName = memberName;
        this.memberage = memberage;
        this.societyname = societyname;
        this.memberemail = memberemail;
        this.memberregno = memberregno;
    }

    public societyMember(String memberName, String memberage, String memberemail, String memberregno) {
        this.memberName = memberName;
        this.memberage = memberage;
        this.memberemail = memberemail;
        this.memberregno = memberregno;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberage() {
        return memberage;
    }

    public String getSocietyname() {
        return societyname;
    }

    public String getMemberemail() {
        return memberemail;
    }

    public String getMemberregno() {
        return memberregno;
    }

    public int getSocietyid() {
        return societyid;
    }
}
