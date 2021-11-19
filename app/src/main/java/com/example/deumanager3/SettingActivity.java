package com.example.deumanager3;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button logoutButton = findViewById(R.id.logout_button);
        Button versionButton = findViewById(R.id.version_button);
        Button queryButton = findViewById(R.id.query_button);
//        Button developButton = findViewById(R.id.develop_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SettingActivity.this,GoogleLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show();

            }
        });
        versionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                VersionDialogFragment dialog = new VersionDialogFragment();
                dialog.show(fragmentManager, "abc");
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        developButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                android.app.FragmentManager fragmentManager = getFragmentManager();
//                com.example.deumanager3.DeveloperDialogFragment dialog = new com.example.deumanager3.DeveloperDialogFragment();
//                dialog.show(fragmentManager, "abc");
//            }
//        });

    }


}
