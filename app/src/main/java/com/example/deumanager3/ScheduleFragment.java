package com.example.deumanager3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.deumanager3.model.ScheduleModel;
import com.example.deumanager3.singleton.Post;
import com.example.deumanager3.singleton.Schedule;
import com.example.deumanager3.singleton.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.example.deumanager3.ScheduleDialogFragment;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends ToolBarFragment {
    private static final String DIALOG_SCHEDULE = "DialogSchedule";
    private static ScheduleFragment sFragment = new ScheduleFragment();
    private static ScheduleModel scheduleModel = new ScheduleModel();
    private Schedule schedule = new Schedule();
    private List<Schedule> mSchedules = new ArrayList<>();
    private boolean scheduleDelete = false;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseDatabase firebaseDatabase;
    private ChildEventListener childEventListener;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();
    private TextView monday[] = new TextView[8];
    private TextView tuesday[] = new TextView[8];
    private TextView wednesday[] = new TextView[8];
    private TextView thursday[] = new TextView[8];
    private TextView friday[] = new TextView[8];
    private String classDay;
    private String classRoom;
    private String classTime;
    private String className;
    private String sAuthorUid = User.getInstance().getUid();
    public void addScheduleModel(Schedule schedule) { mSchedules.add(schedule);}
    private ScheduleFragment scheduleFragment;
    private FragmentManager fragmentManager;
    private String sDay[] = {"월요일","화요일","수요일","목요일","금요일"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        monday[0] = view.findViewById(R.id.monday1);
        monday[1] = view.findViewById(R.id.monday2);
        monday[2] = view.findViewById(R.id.monday3);
        monday[3] = view.findViewById(R.id.monday4);
        monday[4] = view.findViewById(R.id.monday5);
        monday[5] = view.findViewById(R.id.monday6);
        monday[6] = view.findViewById(R.id.monday7);
        monday[7] = view.findViewById(R.id.monday8);

        tuesday[0] = view.findViewById(R.id.tuesday1);
        tuesday[1] = view.findViewById(R.id.tuesday2);
        tuesday[2] = view.findViewById(R.id.tuesday3);
        tuesday[3] = view.findViewById(R.id.tuesday4);
        tuesday[4] = view.findViewById(R.id.tuesday5);
        tuesday[5] = view.findViewById(R.id.tuesday6);
        tuesday[6] = view.findViewById(R.id.tuesday7);
        tuesday[7] = view.findViewById(R.id.tuesday8);

        wednesday[0] = view.findViewById(R.id.wednesday1);
        wednesday[1] = view.findViewById(R.id.wednesday2);
        wednesday[2] = view.findViewById(R.id.wednesday3);
        wednesday[3] = view.findViewById(R.id.wednesday4);
        wednesday[4] = view.findViewById(R.id.wednesday5);
        wednesday[5] = view.findViewById(R.id.wednesday6);
        wednesday[6] = view.findViewById(R.id.wednesday7);
        wednesday[7] = view.findViewById(R.id.wednesday8);

        thursday[0] = view.findViewById(R.id.thursday1);
        thursday[1] = view.findViewById(R.id.thursday2);
        thursday[2] = view.findViewById(R.id.thursday3);
        thursday[3] = view.findViewById(R.id.thursday4);
        thursday[4] = view.findViewById(R.id.thursday5);
        thursday[5] = view.findViewById(R.id.thursday6);
        thursday[6] = view.findViewById(R.id.thursday7);
        thursday[7] = view.findViewById(R.id.thursday8);

        friday[0] = view.findViewById(R.id.friday1);
        friday[1] = view.findViewById(R.id.friday2);
        friday[2] = view.findViewById(R.id.friday3);
        friday[3] = view.findViewById(R.id.friday4);
        friday[4] = view.findViewById(R.id.friday5);
        friday[5] = view.findViewById(R.id.friday6);
        friday[6] = view.findViewById(R.id.friday7);
        friday[7] = view.findViewById(R.id.friday8);

        UpdateSchedule();

        for ( int i = 0 ; i < 8 ; i++) {
            int sTime = i + 1;
            int j = i;
            monday[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(monday[j].getText().equals("")){
                        Toast.makeText(getActivity(),"강의가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        DeleteClass(view, sDay[0], sTime);
                    }
                }
            });
            tuesday[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tuesday[j].getText().equals("")){
                        Toast.makeText(getActivity(),"강의가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        DeleteClass(view, sDay[1], sTime);
                    }

                }
            });
            wednesday[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(wednesday[j].getText().equals("")){
                        Toast.makeText(getActivity(),"강의가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        DeleteClass(view, sDay[2], sTime);
                    }

                }
            });
            thursday[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(thursday[j].getText().equals("")){
                        Toast.makeText(getActivity(),"강의가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        DeleteClass(view, sDay[3], sTime);
                    }

                }
            });
            friday[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(friday[j].getText().equals("")){
                        Toast.makeText(getActivity(),"강의가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        DeleteClass(view, sDay[4], sTime);
                    }

                }
            });
        }

        return view;
    }
    public void UpdateSchedule() {
        for (int i = 1; i <= 8; i++){
            int j = i-1;
            String istring = Integer.toString(i);
            databaseReference.child("시간표").child(sAuthorUid).child("월요일").child(istring).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Schedule schedule1 = snapshot.getValue(Schedule.class);
                    if(snapshot.getValue() == null){
                        monday[j].setText(null);
                    }
                    else {
                        className = schedule1.getClassName();
                        classRoom = schedule1.getClassRoom();
                        monday[j].setText(String.format("%s\n%s",className,classRoom));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ScheduleFragment", String.valueOf(error.toException())); // 에러문 출력
                }
            });
            databaseReference.child("시간표").child(sAuthorUid).child("화요일").child(istring).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Schedule schedule1 = snapshot.getValue(Schedule.class);
                    if(snapshot.getValue() == null){
                        tuesday[j].setText(null);
                    }
                    else {
                        className = schedule1.getClassName();
                        classRoom = schedule1.getClassRoom();
                        tuesday[j].setText(String.format("%s\n%s",className,classRoom));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ScheduleFragment", String.valueOf(error.toException())); // 에러문 출력
                }
            }); databaseReference.child("시간표").child(sAuthorUid).child("수요일").child(istring).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Schedule schedule1 = snapshot.getValue(Schedule.class);
                    if(snapshot.getValue() == null){
                        wednesday[j].setText(null);
                    }
                    else {
                        className = schedule1.getClassName();
                        classRoom = schedule1.getClassRoom();
                        wednesday[j].setText(String.format("%s\n%s",className,classRoom));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ScheduleFragment", String.valueOf(error.toException())); // 에러문 출력
                }
            }); databaseReference.child("시간표").child(sAuthorUid).child("목요일").child(istring).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Schedule schedule1 = snapshot.getValue(Schedule.class);
                    if(snapshot.getValue() == null){
                        thursday[j].setText(null);
                    }
                    else {
                        className = schedule1.getClassName();
                        classRoom = schedule1.getClassRoom();
                        thursday[j].setText(String.format("%s\n%s",className,classRoom));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ScheduleFragment", String.valueOf(error.toException())); // 에러문 출력
                }
            }); databaseReference.child("시간표").child(sAuthorUid).child("금요일").child(istring).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Schedule schedule1 = snapshot.getValue(Schedule.class);
                    if(snapshot.getValue() == null){
                        friday[j].setText(null);
                    }
                    else {
                        className = schedule1.getClassName();
                        classRoom = schedule1.getClassRoom();
                        friday[j].setText(String.format("%s\n%s",className,classRoom));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ScheduleFragment", String.valueOf(error.toException())); // 에러문 출력
                }
            });
        }
    }

    public void DeleteClass(View v, String sDay, int sTime) {
        FragmentManager manager = getFragmentManager();
        ScheduleDeleteDialogFragment deleteDialogFragment = new ScheduleDeleteDialogFragment();
        deleteDialogFragment.show(manager, DIALOG_SCHEDULE);
        deleteDialogFragment.setDeleteDialogResult(new ScheduleDeleteDialogFragment.OnMyDeleteDialogResult() {
            @Override
            public void delete(boolean result) {
                scheduleDelete = result;
                if(scheduleDelete){
                    databaseReference.child("시간표").child(sAuthorUid).child(sDay).child(Integer.toString(sTime)).setValue(null);
                    UpdateSchedule();
                }
            }
        });
    }
}