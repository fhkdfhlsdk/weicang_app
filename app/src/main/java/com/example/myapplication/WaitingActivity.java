package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class WaitingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WaitingActivity.this.finish();
                Toast.makeText(WaitingActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        },2000);
    }
}



