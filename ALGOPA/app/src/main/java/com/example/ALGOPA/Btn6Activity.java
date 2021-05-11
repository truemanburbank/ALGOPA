package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Btn6Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn6);

        Button imageButton = (Button) findViewById(R.id.btn37);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn37Activity.class);
                startActivity(intent);
            }
        }); Button imageButton1 = (Button) findViewById(R.id.btn38);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn38Activity.class);
                startActivity(intent);
            }
        });Button imageButton2 = (Button) findViewById(R.id.btn39);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn39Activity.class);
                startActivity(intent);
            }
        });Button imageButton3 = (Button) findViewById(R.id.btn40);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn40Activity.class);
                startActivity(intent);
            }
        });Button imageButton4 = (Button) findViewById(R.id.btn41);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn41Activity.class);
                startActivity(intent);
            }
        });Button imageButton5 = (Button) findViewById(R.id.btn42);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn42Activity.class);
                startActivity(intent);
            }
        });
    }
}
