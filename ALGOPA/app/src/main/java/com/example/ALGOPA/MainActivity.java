package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imageButton = (Button)findViewById(R.id.btn1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn1Activity.class);
                startActivity(intent);
            }
        });Button imageButton1 = (Button)findViewById(R.id.btn2);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn2Activity.class);
                startActivity(intent);
            }
        });Button imageButton2 = (Button)findViewById(R.id.btn3);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(getApplicationContext(), Btn3Activity.class);
                startActivity(intent);
            }
        });Button imageButton3 = (Button)findViewById(R.id.btn4);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn4Activity.class);
                startActivity(intent);
            }
        });Button imageButton4 = (Button)findViewById(R.id.btn5);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn5Activity.class);
                startActivity(intent);
            }
        });Button imageButton5 = (Button)findViewById(R.id.btn6);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn6Activity.class);
                startActivity(intent);
            }

        });
    }
}