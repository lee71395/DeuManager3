package com.example.deumanager3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deumanager3.model.OnDataChangedListener;
import com.example.deumanager3.singleton.CalendarSingle;
import com.example.deumanager3.singleton.Dday;
import com.example.deumanager3.singleton.Schedule;
import com.example.deumanager3.singleton.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends ToolBarFragment {

    private static final String DIALOG_DDAY = "DialogDday";
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase database;
    private TextView tname;
    private TextView email;
    private User user1;
    private ImageView phone;
    private ImageView map;
    private ImageView bus;
    private ImageView homepage;
    private ImageView book;
    private ImageView heart;
    private boolean DdayDelete = false;
    private static final int ADD_NOTE = 44;
    private OnDataChangedListener onDataChangedListener;

    private TextView tclass[] = new TextView[8];
    private TextView ddayText;
    private TextView todayText;
    private TextView resultText;
    private Button resetButton;
    private TextView ddayText2;
    private TextView resultText2;
    private Button resetButton2;
    private Button addTextBtn;
    private int check=0;

    private int tYear;           //오늘 연월일 변수
    private int tMonth;
    private int tDay;
    private int tDoW;
    private String [] dayArray = {"일", "월", "화", "수", "목", "금", "토"};
    private String [] dAry = {"월요일", "화요일", "수요일", "목요일", "금요일"};
    private String DayoW;
    private int dYear=1;        //디데이 연월일 변수
    private int dMonth=1;
    private int dDay=1;
    private int dYear2 = 1;
    private int dMonth2 = 1;
    private int dDay2 = 1;
    private String dAuthorUid = null;
    private String myUid = dAuthorUid;
    private TextView todayschedule;

    private long d;
    private long t;
    private long r;
    private int n;
    private int resultNumber=0;

    static final int DATE_DIALOG_ID=0;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//    private User user;


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setToolbar();
        book = view.findViewById(R.id.book);
        heart = view.findViewById(R.id.heart);
        readDDay();
        readDDay2();
        loadschedule();

        tname=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        ddayText=view.findViewById(R.id.dday);
        todayText=view.findViewById(R.id.today);
        resultText=view.findViewById(R.id.result);
        resetButton=view.findViewById(R.id.resetButton);
        tname.setText(user.getDisplayName());
        email.setText(user.getEmail());
        ddayText2 = view.findViewById(R.id.dday2);
        resultText2 = view.findViewById(R.id.result2);
        resetButton2 = view.findViewById(R.id.resetButton2);
        todayschedule = view.findViewById(R.id.todaytext);

        tclass[0] = view.findViewById(R.id.tclass1);
        tclass[1] = view.findViewById(R.id.tclass2);
        tclass[2] = view.findViewById(R.id.tclass3);
        tclass[3] = view.findViewById(R.id.tclass4);
        tclass[4] = view.findViewById(R.id.tclass5);
        tclass[5] = view.findViewById(R.id.tclass6);
        tclass[6] = view.findViewById(R.id.tclass7);
        tclass[7] = view.findViewById(R.id.tclass8);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dDateSetListener, tYear, tMonth, tDay);
                datePickerDialog.show();
                check=1;
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(getActivity(), dDateSetListener, tYear, tMonth, tDay);
                datePickerDialog2.show();
                check=2;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDday1();
            }
        });
        resetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDday2();
            }
        });

        Calendar calendar = Calendar.getInstance();    //현재 날짜
        tYear = calendar.get(Calendar.YEAR);
        tMonth = calendar.get(Calendar.MONTH);
        tDay = calendar.get(Calendar.DAY_OF_MONTH);
        tDoW = calendar.get(Calendar.DAY_OF_WEEK) - 1; //배열과 맞추기 위해 -1
        Calendar dCalendar = Calendar.getInstance();
        dCalendar.set(dYear, dMonth, dDay);
        n = (tDoW % 7);
        DayoW = dayArray[n];

        t = calendar.getTimeInMillis();                //오늘 날짜를 밀리타임으로 바꿈
        d = dCalendar.getTimeInMillis();               //디데이날짜를 밀리타임으로 바꿈
        r = (d - t) / (24 * 60 * 60 * 1000);           //디데이 날짜에서 오늘 날짜를 뺀 값을 '일'단위로 바꿈

        resultNumber = (int) r + 1;
        updateDisplay();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar back키 누르면 동작
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void TodaySchedule(){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        int cnt = 0, chk;
        if(0 <= n && n <= 5){
            for(int i = 1; i <= 8; i++) {
                int j = i - 1;

                String istring = Integer.toString(i);
                mDatabaseRef.child("시간표").child(user.getUid()).child(dAry[n]).child(istring).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Schedule schedule1 = snapshot.getValue(Schedule.class);
                        if(snapshot.getValue() == null){
                            tclass[j].setText(String.format("%s교시 공강", istring.toString()));
                        }
                        else {
                            String className = schedule1.getClassName();
                            String classRoom = schedule1.getClassRoom();
                            tclass[j].setText(String.format("%s교시 %s %s호",istring.toString(), className,classRoom));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("TodaySchedule", String.valueOf(error.toException())); // 에러문 출력
                    }
                });
            }
        }
    }

    private void updateDisplay() {

        todayText.setText(String.format("%d년 %d월 %d일 %s요일",tYear, tMonth + 1,tDay, DayoW));
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        TodaySchedule();

    }//디데이 날짜가 오늘날짜보다 뒤에오면 '-', 앞에오면 '+'를 붙인다

    private DatePickerDialog.OnDateSetListener dDateSetListener=new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet( DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (check == 1) {
                // TODO Auto-generated method stub
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                dYear = year;
                dMonth = monthOfYear;
                dDay = dayOfMonth;
                dAuthorUid = User.getInstance().getUid();
                final Calendar dCalendar = Calendar.getInstance();
                dCalendar.set(dYear, dMonth, dDay);

                d = dCalendar.getTimeInMillis();
                r = (d - t) / (24 * 60 * 60 * 1000);
                writeNewUser(dYear, dMonth, dDay, dAuthorUid);
                resultNumber = (int) r;
                updateDisplay();

            }
            else if(check == 2) {
                // TODO Auto-generated method stub
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                dYear2 = year;
                dMonth2 = monthOfYear;
                dDay2 = dayOfMonth;
                dAuthorUid = User.getInstance().getUid();
                final Calendar dCalendar = Calendar.getInstance();
                dCalendar.set(dYear2, dMonth2, dDay2);

                d = dCalendar.getTimeInMillis();
                r = (d - t) / (24 * 60 * 60 * 1000);
                writeNewUser2(dYear2, dMonth2, dDay2, dAuthorUid);
                resultNumber = (int) r;
                updateDisplay();

            }
        }
        private void writeNewUser(int year, int month, int day, String authorUid) {
            if (check == 1) {
                Dday dday = new Dday(year, month, day, authorUid);

                mDatabaseRef.child("Dday").child(authorUid).setValue(dday)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
            }

        }
        private void writeNewUser2 (int year2, int month2, int day2, String authorUid) {
            if (check == 2) {
                Dday dday = new Dday(year2, month2, day2, authorUid);

                mDatabaseRef.child("Dday2").child(authorUid).setValue(dday)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
            }
        }
    };
    public void ResetDday1() {
        FragmentManager manager = getFragmentManager();
        DdayDeleteDialogFragment deleteDialogFragment = new DdayDeleteDialogFragment();
        deleteDialogFragment.show(manager, DIALOG_DDAY);
        deleteDialogFragment.setDeleteDialogResult(new DdayDeleteDialogFragment.OnMyDeleteDialogResult() {
            @Override
            public void delete(boolean result) {
                DdayDelete = result;
                if(DdayDelete){
                    mDatabaseRef.child("Dday").child(user.getUid()).setValue(null);
                    ddayText.setText("날짜를 입력하세요");
                    resultText.setText("D-Day");
                }
            }
        });
    }
    public void ResetDday2() {
        FragmentManager manager = getFragmentManager();
        DdayDeleteDialogFragment deleteDialogFragment = new DdayDeleteDialogFragment();
        deleteDialogFragment.show(manager, DIALOG_DDAY);
        deleteDialogFragment.setDeleteDialogResult(new DdayDeleteDialogFragment.OnMyDeleteDialogResult() {
            @Override
            public void delete(boolean result) {
                DdayDelete = result;
                if(DdayDelete){
                    mDatabaseRef.child("Dday2").child(user.getUid()).setValue(null);
                    ddayText2.setText("날짜를 입력하세요");
                    resultText2.setText("D-Day");
                }
            }
        });
    }
    private void readDDay() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("Dday").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@Nullable DataSnapshot snapshot) {
                Dday dday = snapshot.getValue(Dday.class);
                if(snapshot.getValue() == null) {
                    return;
                }else{
                    int Year = dday.getYear();
                    int Month = dday.getMonth();
                    int Day = dday.getDay();

                    ddayText.setText(String.format("%d년 %d월 %d일", Year, Month, Day));
                    Calendar calendar = Calendar.getInstance();
                    Calendar dCalendar = Calendar.getInstance();
                    calendar.set(tYear,tMonth+1,tDay);
                    dCalendar.set(Year,Month,Day);
                    long t = calendar.getTimeInMillis();
                    long d = dCalendar.getTimeInMillis();
                    long r = (d - t) / (24 * 60 * 60 * 1000);
                    int resultNumber = (int) r;
                    if(resultNumber > 0){
                        resultText.setText(String.format("D-%d",resultNumber));
                    }else if(resultNumber == 0) {
                        resultText.setText("D-day");
                    }
                    else {
                        int absR=Math.abs(resultNumber); // 절대값 반환
                        resultText.setText(String.format("D+%d",absR));
                    }
                }
            }

            @Override
            public void onCancelled(@Nullable DatabaseError error) {

            }
        });

    }
    private void readDDay2() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("Dday2").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@Nullable DataSnapshot snapshot) {
                Dday dday = snapshot.getValue(Dday.class);
                if(snapshot.getValue() == null) {
                    return;
                }else{
                    int Year = dday.getYear();
                    int Month = dday.getMonth();
                    int Day = dday.getDay();

                    ddayText2.setText(String.format("%d년 %d월 %d일", Year, Month, Day));
                    Calendar calendar = Calendar.getInstance();
                    Calendar dCalendar = Calendar.getInstance();
                    calendar.set(tYear,tMonth+1,tDay);
                    dCalendar.set(Year,Month,Day);
                    long t = calendar.getTimeInMillis();
                    long d = dCalendar.getTimeInMillis();
                    long r = (d - t) / (24 * 60 * 60 * 1000);
                    int resultNumber = (int) r;
                    if(resultNumber > 0){
                        resultText2.setText(String.format("D-%d",resultNumber));
                    }else if (resultNumber == 0) {
                        resultText2.setText("D-Day");
                    }
                    else {
                        int absR=Math.abs(resultNumber); // 절대값 반환
                        resultText2.setText(String.format("D+%d",absR));
                    }
                }
            }

            @Override
            public void onCancelled(@Nullable DatabaseError error) {

            }
        });

    }
    private void loadschedule() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("캘린더").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            private List<CalendarSingle> gCalendarSingles;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int get_Year = ds.getValue(CalendarSingle.class).getYear();
                    int get_Month = ds.getValue(CalendarSingle.class).getMonth();
                    int get_Day = ds.getValue(CalendarSingle.class).getDay();
                    String getNote = ds.getValue(CalendarSingle.class).getNote();
                    if(tYear == get_Year && tMonth + 1 == get_Month &&
                            tDay == get_Day){
                        todayschedule.setText(getNote);
                        break;
                    }
                    else{
                        todayschedule.setText("");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setName(String name) {
        tname.setText(name);
    }

}