package com.example.deumanager3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.example.deumanager3.model.CalendarModel;
import com.example.deumanager3.singleton.CalendarSingle;
import com.example.deumanager3.singleton.Dday;
import com.example.deumanager3.singleton.Schedule;
import com.example.deumanager3.singleton.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private CalendarModel calendarModel;
    private MyEventDay myEventDay;
    private EditText noteEditText;
    private CalendarSingle calendarSingle;
    private String cAuthoruid = User.getInstance().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_write);

        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        Button addbutton = (Button) findViewById(R.id.addNoteButton);
        noteEditText = (EditText) findViewById(R.id.noteEditText);
        calendarModel = new CalendarModel("캘린더");
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                if(noteEditText.getText().toString()==null) {
                    finish();
                }
                myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.ic_message_black_48dp, noteEditText.getText().toString());
                returnIntent.putExtra(CalendarActivity.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                sendCalendar();

                finish();
            }
        });
    }

    public int getYear() {
        return this.myEventDay.getYear();
    }

    public int getMonth() {
        return this.myEventDay.getMonth();
    }

    public int getDay() {
        return this.myEventDay.getDay();
    }

    public void sendCalendar() {
        calendarModel.writeCalendar("캘린더",getYear(),getMonth(),getDay(),noteEditText.getText().toString(),cAuthoruid);
        Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show();

    }
}
