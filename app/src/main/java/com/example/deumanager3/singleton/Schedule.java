package com.example.deumanager3.singleton;

public class Schedule {
    private String className;
    private String classRoom;
    private String classDay;
    private String classTime;
    private String authorUid;

    public Schedule() {

    }

    public Schedule(String className, String classRoom, String classDay, String classTime, String authorUid) {
        this.className = className;
        this.classRoom = classRoom;
        this.classDay = classDay;
        this.classTime = classTime;
        this.authorUid = User.getInstance().getUid();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {this.className = className;}

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {this.classRoom = classRoom;}

    public String getClassDay() {
        return classDay;
    }

    public void setClassDay(String classDay) {this.classDay = classDay;}

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) { this.classTime = classTime;}


    public static Schedule newSchedule(String className, String classRoom, String classDay, String classTime, String authorUid) {
        return new Schedule(className, classRoom, classDay, classTime, authorUid);
    }
}
