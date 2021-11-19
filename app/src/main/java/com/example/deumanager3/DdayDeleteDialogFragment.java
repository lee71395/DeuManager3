package com.example.deumanager3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.View;

import com.example.deumanager3.singleton.Schedule;
import com.example.deumanager3.singleton.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DdayDeleteDialogFragment extends DialogFragment {
    private boolean delete = false;
    private OnMyDeleteDialogResult mOnMyDeleteDialogResult;
    private Schedule schedule = new Schedule();



    public interface OnMyDeleteDialogResult{

        void delete(boolean result);

    }
    public void setDeleteDialogResult(OnMyDeleteDialogResult dialogResult){

        mOnMyDeleteDialogResult = dialogResult;

    }
    public void DeleteSchedule() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dday_delete_dialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity()).setView(view);
        return alertDialog
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete = true;
                        mOnMyDeleteDialogResult.delete(delete);
                    }
                })
                .create();
    }
}
