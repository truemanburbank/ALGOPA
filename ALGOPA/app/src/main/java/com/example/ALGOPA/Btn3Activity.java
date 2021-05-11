package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Btn3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn3);

        Button imageButton = (Button) findViewById(R.id.btn19);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn19Activity.class);
                startActivity(intent);
            }
        });Button imageButton1 = (Button) findViewById(R.id.btn20);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn20Activity.class);
                startActivity(intent);
            }
        });Button imageButton2 = (Button) findViewById(R.id.btn21);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn21Activity.class);
                startActivity(intent);
            }
        });Button imageButton3 = (Button) findViewById(R.id.btn22);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn22Activity.class);
                startActivity(intent);
            }
        });Button imageButton4 = (Button) findViewById(R.id.btn23);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn23Activity.class);
                startActivity(intent);
            }
        });Button imageButton5 = (Button) findViewById(R.id.btn24);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn24Activity.class);
                startActivity(intent);
            }
        });
    }
}
