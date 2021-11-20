package com.example.deumanager3;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BoardClassRecommendationFragment extends CommonBoardFragment {

    public BoardClassRecommendationFragment() {}

    @Override
    public DatabaseReference getRef() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference.child("추천강의");
    }

    @Override
    public String getPostType() {
        return "추천강의";
    }

}
