package com.example.deumanager3;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

public class DatePickerDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public void onTimeSet( TimePicker timePicker, int i, int i1 ) {

    }

//    @Override
//    public Dialog onCreateDialog( Bundle savedInstanceState ) {
//        Calendar calendar = Calendar.getInstance();
////        int hour = Calendar.get(Calendar.getInstance());
////        int min = Calendar.get((Calendar.MINUTE));
////        TimePickerDialog timePickerDialog = new TimePickerDialog(
////                getContext(), this, hour, min, DateFormat.getDateInstance()
////        );
//        return ;
////}
//    }
}
