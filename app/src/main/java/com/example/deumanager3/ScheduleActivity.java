package com.example.deumanager3;


import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.deumanager3.model.ScheduleModel;
import com.example.deumanager3.singleton.Schedule;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    private List<TextView> mTextViews;
    private static final String DIALOG_SCHEDULE = "DialogSchedule";
    private ActionBar actionBar;
    private TextView toolbarText;
    private ImageButton writeButton;
    private FragmentManager fragmentManager;
    private static ScheduleFragment scheduleFragment;
    private static NoticeFragment sNoticeFragment = new NoticeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        toolbarText = findViewById(R.id.toolbartext);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        toolbarText.setText("SCHEDULE");
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragment_schedule, new ScheduleFragment()).commit();
        writeButton = findViewById(R.id.writing_button);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                ScheduleDialogFragment dialogFragment = new ScheduleDialogFragment();
                dialogFragment.show(manager, DIALOG_SCHEDULE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
