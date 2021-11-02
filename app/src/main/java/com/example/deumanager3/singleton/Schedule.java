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

    public String getClassRoom() {
        return classRoom;
    }

    public String getClassDay() {
        return classDay;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getClassUid() { return authorUid; }


    public static Schedule newSchedule(String className, String classRoom, String classDay, String classTime, String authorUid) {
        return new Schedule(className, classRoom, classDay, classTime, authorUid);
    }


}
