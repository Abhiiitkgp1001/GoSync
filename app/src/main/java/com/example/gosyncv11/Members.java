package com.example.gosyncv11;

public class Members {
    String member_name, member_id;

    public Members() {
    }

    public Members(String member_name, String member_id) {
        this.member_name = member_name;
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
}
