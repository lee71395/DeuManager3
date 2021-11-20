package com.example.deumanager3;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.deumanager3.model.UserModel;


public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView toolbarText;
    private ImageButton settingButton;
    private FragmentManager fragmentManager;
    private BackPressCloseHandler backPressCloseHandler;
    private Button button;
    private static HomeFragment sHomeFragment = new HomeFragment();
    private static ScheduleFragment scheduleFragment = new ScheduleFragment();
    private static NoticeFragment sNoticeFragment = new NoticeFragment();
    private static CalendarActivity calendarActivity= new CalendarActivity();
    private static ScheduleActivity scheduleActivity = new ScheduleActivity();
    private UserModel user1 = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserModel userModel = new UserModel();
        userModel.readUserData();
        fragmentManager = getSupportFragmentManager();
        toolbarText = findViewById(R.id.toolbartext);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

        settingButton = findViewById(R.id.setting_button);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        user1.readUserData();

        replaceFragment(sHomeFragment);
        toolbarText.setText("홈");
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bnv);

        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_one://홈
                        replaceFragment(sHomeFragment);
                        toolbarText.setText("홈");
                        return true;
                    case R.id.action_two://시간표
                        Intent scheduleIntent = new Intent (MainActivity.this, ScheduleActivity.class);
                        startActivity(scheduleIntent);

                        return true;
                    case R.id.action_three://캘린더
                        Intent calenderIntent = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(calenderIntent);

                        return true;
                    case R.id.action_four://공지
                        replaceFragment(sNoticeFragment);
                        toolbarText.setText("공지사항");
                        return true;
                    case R.id.action_five://게시판
                        Intent boardIntent = new Intent(MainActivity.this, BoardActivity.class);
                        startActivity(boardIntent);

                        return true;
                }
                return false;
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);

    }

    private void replaceFragment(Fragment fm) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fm).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {

        }
        return true;
    }

    @Override

    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

}