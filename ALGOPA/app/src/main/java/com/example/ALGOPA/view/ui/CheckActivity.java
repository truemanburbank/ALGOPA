package com.example.ALGOPA.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ALGOPA.R;
import com.example.ALGOPA.services.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    public static Context mContext;
    Users users;
    RadioButton normal, pro;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        mContext = this;

        normal = findViewById(R.id.normal_member);
        pro = findViewById(R.id.pro_member);

    }

    public void addNormal() {
        users = new Users();
        databaseReference = database.getInstance().getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (normal.isChecked()) {
            users.setMember("Normal Member");
            databaseReference.child(firebaseAuth.getUid()).child("member").setValue(users);
        }
    }

    public void addPro() {
        users = new Users();
        databaseReference = database.getInstance().getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (pro.isChecked()) {
            users.setMember("Pro Member");
            databaseReference.child(firebaseAuth.getUid()).child("member").setValue(users);
        }
    }
}