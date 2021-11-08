package com.example.deumanager3.singleton;


public class CalendarSingle {

    private String note;
    private int year;
    private int month;
    private int day;
    private String type;

    public CalendarSingle() {

    }
    private static class Singleton {
        private static final CalendarSingle INSTANCE = new CalendarSingle();
    }
    //Singleton으로 동일한 방법으로 객체 참조.
    public static CalendarSingle getInstance() {
        return CalendarSingle.Singleton.INSTANCE;
    }

    public String getNote() {
        return note;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String getType() { return  this.type;}

    public CalendarSingle( String note, int y, int m, int d, String type) {
        this.note = note;
        this.year =y;
        this.month=m;
        this.day=d;
        this.type=type;
    }

    public static CalendarSingle newCalendar( String note, int y, int m, int d, String type) {
        return new CalendarSingle(note,y,m,d,type);
    }

}
