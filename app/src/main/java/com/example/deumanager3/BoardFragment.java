package com.example.deumanager3;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BoardFragment extends ToolBarFragment {
    private ActionBar actionBar;
    private TextView toolbarText;

    @NonNull
    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        setToolbar();
        return view;
    }
}
