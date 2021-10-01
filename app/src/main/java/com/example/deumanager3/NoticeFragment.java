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

    private ImageButton UniNoticeButton;
    private ImageButton scheduleButton;
    private ImageButton majorNoticeButton;
    private Button foodButton;
    private ImageButton noticeButton;

//    @NonNull
//    public static NoticeFragment newInstance() {
//        return new NoticeFragment();
//    }

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        setToolbar();

        UniNoticeButton = view.findViewById(R.id.notice_button);
        majorNoticeButton = view.findViewById(R.id.major_notice_button);
//        foodButton = view.findViewById(R.id.food_button);
        scheduleButton = view.findViewById(R.id.university_schedule_button);
        noticeButton = view.findViewById(R.id.notice_button2);

        UniNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deu.ac.kr/www/board/3"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://it.ajou.ac.kr/it/community/community01.jsp"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        majorNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://computer.deu.ac.kr/computer/index.do"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deu.ac.kr/www/academic_calendar"));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });

        return view;
    }

}
