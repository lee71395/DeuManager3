package com.example.deumanager3.model;

import com.example.deumanager3.singleton.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserModel {
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    public UserModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void readUserData() {
        databaseReference.child("User").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    setUserData();
                }
                else {
                    User.getInstance().setUserName(user.getDisplayName());
                    User.getInstance().setUid(user.getUid());
                    User.getInstance().setEmail(user.getEmail());
                    User.getInstance().setDisabled(Objects.requireNonNull(dataSnapshot.getValue(User.class)).isDisabled());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUserData() {
        databaseReference.child("User").child(user.getUid()).child("name").setValue(user.getDisplayName());
        databaseReference.child("User").child(user.getUid()).child("uid").setValue(user.getUid());
        databaseReference.child("User").child(user.getUid()).child("email").setValue(user.getEmail());
        databaseReference.child("User").child(user.getUid()).child("disabled").setValue(false);
    }
}
