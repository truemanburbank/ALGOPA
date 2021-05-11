package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Btn4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn4);

        Button imageButton = (Button) findViewById(R.id.btn25);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn25Activity.class);
                startActivity(intent);
            }
        });Button imageButton1 = (Button) findViewById(R.id.btn26);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn26Activity.class);
                startActivity(intent);
            }
        });Button imageButton2 = (Button) findViewById(R.id.btn27);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn27Activity.class);
                startActivity(intent);
            }
        });Button imageButton3 = (Button) findViewById(R.id.btn28);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn28Activity.class);
                startActivity(intent);
            }
        });Button imageButton4 = (Button) findViewById(R.id.btn29);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn29Activity.class);
                startActivity(intent);
            }
        });Button imageButton5 = (Button) findViewById(R.id.btn30);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn30Activity.class);
                startActivity(intent);
            }
        });
    }
}
