package com.example.deumanager3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class NoticeFragment extends com.example.deumanager3.ToolBarFragment {

    private ImageButton DoorButton;
    private ImageButton DAPButton;
    private ImageButton NoticeButton;
    private ImageButton TimeTableButton;

//    @NonNull
//    public static NoticeFragment newInstance() {
//        return new NoticeFragment();
//    }

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        setToolbar();

        DoorButton = view.findViewById(R.id.door_button);
        DAPButton = view.findViewById(R.id.dap_button);
//        foodButton = view.findViewById(R.id.food_button);
        NoticeButton = view.findViewById(R.id.notice_button);
        TimeTableButton = view.findViewById(R.id.timetable_button);

        DoorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://door.deu.ac.kr/Home/Index"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        DAPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dap.deu.ac.kr/sso/login.aspx"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        NoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deu.ac.kr/www/board/3"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        TimeTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deu.ac.kr/www/academic_calendar"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        //        phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deu.ac.kr/www/tel/21"));
//                intent.setPackage("com.android.chrome");
//                startActivity(intent);
//            }
//        });
//
//        map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deu.ac.kr/www/content/14"));
//                intent.setPackage("com.android.chrome");
//                startActivity(intent);
//            }
//        });

        return view;
    }

}
