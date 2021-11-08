package com.example.deumanager3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.FragmentManager;

import com.example.deumanager3.singleton.Schedule;
import com.example.deumanager3.singleton.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ScheduleDialogFragment extends DialogFragment {
    Fragment fragment = new ScheduleFragment();
    Bundle bundle = new Bundle();
    private DatabaseReference databaseReference;
    OnMyDialogResult myDialogResult;



    public interface OnTimePickerSetListener{
        void onTimePickerSet(String name, String room, int day, int time );
    }
    private EditText className;
    private EditText classRoom;
    private Spinner classDay;
    private Spinner classTime;
    private String cName;
    private String cRoom;
    private String Day;
    private String Time;
    private OnMyDialogResult mDialogResult;
    private String cAuthorUid;
    private int check;



    public interface OnMyDialogResult{

        void finish(Object result);

    }
    public void setDialogResult(OnMyDialogResult dialogResult){

        mDialogResult = dialogResult;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_schedule_dialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity()).setView(view);
        className = view.findViewById(R.id.class_name);
        classRoom = view.findViewById(R.id.class_room);
        classDay = view.findViewById(R.id.class_day);
        classTime = view.findViewById(R.id.class_time);
        databaseReference = FirebaseDatabase.getInstance().getReference();


        return alertDialog
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Schedule schedule = new Schedule(className.getText().toString(),classRoom.getText().toString(),classDay.getSelectedItem().toString(),classTime.getSelectedItem().toString(),cAuthorUid);

                        cName = schedule.getClassName();
                        cRoom = schedule.getClassRoom();
                        Day = schedule.getClassDay();
                        Time = schedule.getClassTime();
                        cAuthorUid = User.getInstance().getUid();
                        System.out.println(cName);
                        System.out.println(cRoom);
                        System.out.println(Day);
                        System.out.println(Time);

                        wirteNewSche(cName,cRoom,Day,Time,cAuthorUid);
                        //mDialogResult.finish(schedule);

//                        createView(schedule.getClassName(), schedule.getClassRoom(), schedule.getClassDay(), schedule.getClassTime());
                    }
                })
                .create();
    }
    private void wirteNewSche(String cname, String croom, String cday, String ctime, String authorUid) {
        Schedule schedule = new Schedule(cname, croom, cday, ctime, authorUid);
            databaseReference.child("시간표").child(authorUid).child(cday).child(ctime).setValue(schedule)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });


    }



}
