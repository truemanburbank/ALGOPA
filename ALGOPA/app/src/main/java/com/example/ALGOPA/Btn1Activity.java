package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Btn1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn1);


        Button imageButton = (Button) findViewById(R.id.btn7);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn7Activity.class);
                startActivity(intent);
            }
        }); Button imageButton1 = (Button) findViewById(R.id.btn8);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn8Activity.class);
                startActivity(intent);
            }
        });Button imageButton2 = (Button) findViewById(R.id.btn9);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn9Activity.class);
                startActivity(intent);
            }
        });Button imageButton3 = (Button) findViewById(R.id.btn10);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn10Activity.class);
                startActivity(intent);
            }
        });Button imageButton4 = (Button) findViewById(R.id.btn11);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn11Activity.class);
                startActivity(intent);
            }
        });Button imageButton5 = (Button) findViewById(R.id.btn12);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn12Activity.class);
                startActivity(intent);
            }
        });
    }
}
