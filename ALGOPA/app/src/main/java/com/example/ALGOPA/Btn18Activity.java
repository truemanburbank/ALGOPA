package com.example.ALGOPA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Btn18Activity extends AppCompatActivity {
    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 0;
    private  int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn18);

        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(count+"");
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        Button imageButton = (Button)findViewById(R.id.btnexit12);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Btn2Activity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvCount.setText(count+"");
                if(count>5){
                    Toast.makeText(getApplicationContext(),"수량을 6개이상 구매할수없습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                tvCount.setText(count+"");

                if(count <0){
                    Toast.makeText(getApplicationContext(),"수량을 0개미만으로 설정할수없습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });Button imageButton1 = (Button)findViewById(R.id.addbtn12);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Btn2Activity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"구입을 완료하였습니다.",Toast.LENGTH_LONG).show();

            }
        });
    }
}