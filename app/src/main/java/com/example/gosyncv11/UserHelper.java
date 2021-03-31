package com.example.gosyncv11;

public class UserHelper {

    String startup_name, motto, about;
    long contact_no;
    public UserHelper() {
    }

    public UserHelper(String startup_name, String motto, String about, long contact_no) {
        this.startup_name = startup_name;
        this.motto = motto;
        this.about = about;
        this.contact_no = contact_no;
    }

    public String getStartup_name() {
        return startup_name;
    }

    public void setStartup_name(String startup_name) {
        this.startup_name = startup_name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public long getContact_no() {
        return contact_no;
    }

    public void setContact_no(long contact_no) {
        this.contact_no = contact_no;
    }
}
