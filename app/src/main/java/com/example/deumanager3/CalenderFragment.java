package com.example.deumanager3;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalenderFragment extends com.example.parksinyoung.moblieprogramming_syjy.ToolBarFragment {

    @NonNull
    public static CalenderFragment newInstance() {
        return new CalenderFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        setToolbar();
       return view;
    }

}
