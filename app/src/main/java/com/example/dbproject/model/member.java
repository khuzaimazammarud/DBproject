package com.example.dbproject.model;

public class member {

    private int user_id;
    private int memberid;
    private String name;
    private String email;
    private String password;
    private String userregno;
    private int societyid;
    private int age;


    public member(int user_id, int memberid, String name, String email, String password, String userregno) {
        this.user_id = user_id;
        this.memberid = memberid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userregno = userregno;
    }

    public int getMemberid() {
        return memberid;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserregno() {
        return userregno;
    }

    public int getSocietyid() {
        return societyid;
    }

    public int getAge() {
        return age;
    }
}
