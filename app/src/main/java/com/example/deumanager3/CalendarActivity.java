package com.example.deumanager3;

import static java.lang.String.valueOf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.deumanager3.model.OnDataChangedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.deumanager3.model.CalendarModel;
import com.example.deumanager3.model.ScheduleModel;
import com.example.deumanager3.singleton.CalendarSingle;
import com.example.deumanager3.singleton.Schedule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private static final String DIALOG_CALENDAR = "DialogCalendar";
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;
    private CalendarView mCalendarView;
    //    private DatabaseReference mDatabaseRef;
    //private TextView textView;
    private List<EventDay> mEventDays = new ArrayList<>();
    private List<CalendarSingle> gCalendarSingles;
    private CalendarModel calendarModel = new CalendarModel("캘린더");
    private List<EventDay> gEventDats = new ArrayList <>();
    private List<Schedule> mSchedules = new ArrayList <>();
    private ScheduleModel scheduleModel = new ScheduleModel();
    private CalendarSingle mcalendarSingle = new CalendarSingle();

    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private String Type;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private TextView noteView;
    private OnDataChangedListener onDataChangedListener;
    private List<EventDay> eventDay = new ArrayList <>();
    private int get_Year = 0, get_Month = 0, get_Day = 0;
    String getNote = "";
    private Button button;
    private boolean calendarDelete = false;
    private String dPath;

    public String getPostType() {
        return "캘린더";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        button = findViewById(R.id.button2);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        noteView = findViewById(R.id.noteView);
        readCalendar();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteClass();
            }
        });
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.getTime();
//
//        eventDay.add(new EventDay(calendar1,R.drawable.ic_message_black_48dp));
//        mCalendarView.setDate(calendar1);
//        mCalendarView.setEvents(eventDay);


//        readCalendar();

//        calendarModel.addCalendarModel(calendarSingle);
        this.mSchedules = scheduleModel.getSchedules();
        this.gCalendarSingles = calendarModel.getmCalendars();
        Log.i("g Cal 가져옴",valueOf(gCalendarSingles.size()));
//        for(int i = 0; i< gCalendarSingles.size(); i++) {
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(gCalendarSingles.get(i).getYear(), gCalendarSingles.get(i).getMonth(), gCalendarSingles.get(i).getDay());
//            gEventDats.add(new EventDay(calendar,R.drawable.ic_message_black_48dp));
//            mCalendarView.setDate(gEventDats.get(i).getCalendar());
//            mCalendarView.setEvents(gEventDats);
//
//        }

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                readCalendar();

            }
        });
        // textView = findViewById(R.id.preview_note);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

//        ValueEventListener calendarlistener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get CalendarSingle object and use the values to update the UI
////                CalendarSingle calendarSingle = dataSnapshot.getValue(CalendarSingle.class);
////                MyEventDay myEventDay;
////                Intent returnIntent = new Intent();
////                Calendar calendar = Calendar.getInstance();
////                calendar.set(calendarSingle.getYear(),calendarSingle.getMonth(),calendarSingle.getDay());
////                myEventDay = new MyEventDay(calendar, R.drawable.ic_message_black_48dp, calendarSingle.getNote());
////                returnIntent.putExtra(CalendarActivity.RESULT, myEventDay);
////                setResult(Activity.RESULT_OK, returnIntent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("cancelled", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
        // databaseReference.addValueEventListener(calendarlistener);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            com.example.deumanager3.MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }
    }
    private void addNote() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(this, com.example.deumanager3.NotePreviewActivity.class);
        if(eventDay instanceof com.example.deumanager3.MyEventDay){
            intent.putExtra(EVENT, (com.example.deumanager3.MyEventDay) eventDay);
        }
        startActivity(intent);

    }

    private void readCalendar() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("캘린더").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            private List<CalendarSingle> gCalendarSingles;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    get_Year = ds.getValue(CalendarSingle.class).getYear();
                    //            Log.i("연도 가져옴", valueOf(get_Year));
                    get_Month = ds.getValue(CalendarSingle.class).getMonth();
                    //            Log.i("월 가져옴", valueOf(get_Month));
                    get_Day = ds.getValue(CalendarSingle.class).getDay();
                    //            Log.i("일 가져옴", valueOf(get_Day));
                    getNote = ds.getValue(CalendarSingle.class).getNote();
                    //            Log.i("노트 가져옴", valueOf(getNote));
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(get_Year,get_Month-1,get_Day);

                    if(mCalendarView.getSelectedDate().getWeekYear() == get_Year && mCalendarView.getSelectedDate().getTime().getMonth()+1 == get_Month &&
                            mCalendarView.getSelectedDate().getTime().getDate() == get_Day){
                        noteView.setText(getNote);
                        break;
                    }
                    else{
                        noteView.setText("메모 없음");

                    }
                    eventDay.add(new EventDay(calendar,R.drawable.ic_message_black_48dp));
                    mCalendarView.setEvents(eventDay);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void DeleteClass() {
        FragmentManager manager = getSupportFragmentManager();
        CalendarDeleteDialogFragment deleteDialogFragment = new CalendarDeleteDialogFragment();
        deleteDialogFragment.show(manager, DIALOG_CALENDAR);
        deleteDialogFragment.setDeleteDialogResult(new CalendarDeleteDialogFragment.OnMyDeleteDialogResult() {
            @Override
            public void delete(boolean result) {
                calendarDelete = result;
                if(calendarDelete){
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(mCalendarView.getSelectedDate().getWeekYear(),mCalendarView.getSelectedDate().getTime().getMonth()+1,
                                mCalendarView.getSelectedDate().getTime().getDate());
                        dPath = String.valueOf(mCalendarView.getSelectedDate().getWeekYear()) + String.valueOf(mCalendarView.getSelectedDate().getTime().getMonth()+1)
                                + String.valueOf(mCalendarView.getSelectedDate().getTime().getDate());
                        databaseReference.child("캘린더").child(user.getUid()).child(dPath).setValue(null);
                        noteView.setText("메모 없음");
                        finish();//인텐트 종료
                        overridePendingTransition(0, 0);//인텐트 효과 없애기
                        Intent intent = getIntent(); //인텐트
                        startActivity(intent); //액티비티 열기
                        overridePendingTransition(0, 0);//인텐트 효과 없애기
                }
            }
        });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
    }

}