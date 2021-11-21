package com.example.deumanager3.singleton;

import android.widget.DatePicker;

public class Dday {
    int day;
    int month;
    int year;
    private String result;
    private String userName;
    private String authorUid;

    public Dday() {
    }
    public String getUserName() { return userName; }

    public void setUserName(String userName) {this.userName = userName;}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Dday(int year, int monthOfYear, int dayOfMonth, String authorUid) {
        this.day = dayOfMonth;
        this.month = monthOfYear+1;
        this.year = year;
        this.authorUid = User.getInstance().getUid();
    }
}