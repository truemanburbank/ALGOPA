package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Btn2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn2);

        Button imageButton = (Button) findViewById(R.id.btn13);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn13Activity.class);
                startActivity(intent);
            }
        });Button imageButton1 = (Button) findViewById(R.id.btn14);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn14Activity.class);
                startActivity(intent);
            }
        });Button imageButton2 = (Button) findViewById(R.id.btn15);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn15Activity.class);
                startActivity(intent);
            }
        });Button imageButton3 = (Button) findViewById(R.id.btn16);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn16Activity.class);
                startActivity(intent);
            }
        });Button imageButton4 = (Button) findViewById(R.id.btn17);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn17Activity.class);
                startActivity(intent);
            }
        });Button imageButton5 = (Button) findViewById(R.id.btn18);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn18Activity.class);
                startActivity(intent);
    }
});
    }
}