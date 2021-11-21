package com.example.deumanager3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;
import com.example.deumanager3.model.CalendarModel;
import com.example.deumanager3.singleton.CalendarSingle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class NotePreviewActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private CalendarModel calendarModel;
    private CalendarSingle calendarSingle;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_preview);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();        //텍스트뷰 설정하기 위한 intent
        Intent putintet = new Intent(this, CalendarActivity.class);

        TextView note = (TextView) findViewById(R.id.note);
        if (intent != null) {
            Object event = intent.getParcelableExtra(CalendarActivity.EVENT);
            if(event instanceof MyEventDay){
                MyEventDay myEventDay = (MyEventDay)event;
                note.setText(myEventDay.getNote());
                return;
            }
            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));
            }
        }
    }
    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

}
