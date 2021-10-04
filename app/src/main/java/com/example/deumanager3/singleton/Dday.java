package com.example.deumanager3.singleton;

import android.widget.DatePicker;

public class Dday {
   int day;
   int month;
   int year;
   String result;

    public Dday(int year, int monthOfYear, int dayOfMonth, String view) {
    }

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
