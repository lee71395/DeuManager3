package com.example.deumanager3;

import java.util.Calendar;

public class HomeDdayWidget {
    public int caldate(int myear, int mmonth, int mday) {
        try {
            Calendar today = Calendar.getInstance();
            Calendar dday = Calendar.getInstance(); dday.set(myear,mmonth,mday);

            long day = dday.getTimeInMillis()/86400000;
            long tday = today.getTimeInMillis()/86400000; long count = tday - day;
            return (int) count + 1; //날짜는 + 1
            } catch (Exception e)
        { e.printStackTrace(); return - 1; }
    }
}
