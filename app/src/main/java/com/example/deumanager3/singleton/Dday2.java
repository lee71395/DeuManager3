package com.example.deumanager3.singleton;

import android.widget.DatePicker;

public class Dday2 {

    int day2;
    int month2;
    int year2;
    private String result2;
    private String userName;
    private String authorUid;

    public Dday2() {    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) {this.userName = userName;}

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) { this.day2 = day2; }

    public int getMonth2() {
        return month2;
    }

    public void setMonth2(int month2) { this.month2 = month2; }

    public int getYear2() { return year2; }

    public void setYear2(int year2) {
        this.year2 = year2;
    }


    public Dday2(int year2, int monthOfYear2, int dayOfMonth2, String authorUid) {
        this.day2 = dayOfMonth2;
        this.month2 = monthOfYear2+1;
        this.year2 = year2;
//        this.result2 = result2;
        this.authorUid = User.getInstance().getUid();
    }
}