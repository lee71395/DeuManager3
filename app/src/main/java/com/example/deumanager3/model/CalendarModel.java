package com.example.deumanager3.model;

import static java.lang.String.valueOf;

import android.app.Activity;
import android.util.Log;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.deumanager3.CalendarActivity;
import com.example.deumanager3.MyEventDay;
import com.example.deumanager3.R;
import com.example.deumanager3.singleton.CalendarSingle;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarModel {
    private DatabaseReference databaseReference;
    private OnDataChangedListener onDataChangedListener;
    private List <CalendarSingle> mCalendars = new ArrayList <>();
    private CalendarView calendarView;
    private List<EventDay> gEventDats = new ArrayList <>();
    private MyEventDay myEventDay;
    public void addCalendarModel(CalendarSingle calendarSingle) {
        mCalendars.add(calendarSingle);
    }
    public List<CalendarSingle> getmCalendars() {
        return this.mCalendars;
    }
    private CalendarActivity calendarActivity;
    private FirebaseUser user;
    private String dPath;

    public CalendarModel(String postType) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("캘린더").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int getYear = ds.getValue(CalendarSingle.class).getYear();
                    Log.i("가져옴", valueOf(getYear));
                    int getMonth = ds.getValue(CalendarSingle.class).getMonth();
                    Log.i("가져옴", valueOf(getMonth));
                    int getDay = ds.getValue(CalendarSingle.class).getDay();
                    Log.i("가져옴", valueOf(getDay));
                    String getNote = ds.getValue(CalendarSingle.class).getNote();
                    Log.i("가져옴", valueOf(getNote));
                    String getUid = ds.getValue(CalendarSingle.class).getcAuthoruid();
                    CalendarSingle calendarSingle = new CalendarSingle(getNote, getYear, getMonth, getDay, "캘린더",getUid);
                    addCalendarModel(calendarSingle);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }

    public void writeCalendar( String postType, int y, int m, int d, String note, String uid) {
        String dpath = String.valueOf(y) + String.valueOf(m) + String.valueOf(d);
        databaseReference.child(postType).child(uid).child(dpath).setValue(CalendarSingle.newCalendar(note,y,m,d,postType,uid));
    }//등록

}
