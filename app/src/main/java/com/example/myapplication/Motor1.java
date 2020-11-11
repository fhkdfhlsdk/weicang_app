package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Motor1 extends AppCompatActivity {
    String motor_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor1);

        Button forword = (Button) findViewById(R.id.button17);
        Button backword = (Button) findViewById(R.id.button18);
        Button start = (Button) findViewById(R.id.button19);
        Button stop = (Button) findViewById(R.id.button20);

        Intent intent = getIntent();
        motor_num = intent.getStringExtra("motor_num");

        forword.setOnClickListener(new View.OnClickListener() {          //升降电机控制页面
            public void onClick(View view) {
                public_class.Send("forword"+motor_num);
            }
        });

        forword.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View view) {
                try{
                    public_class.Send("forword"+motor_num);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return true;
            }

            public boolean onLong(View view) {
                try{
                    public_class.Send("forword1");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return true;
            }

            public boolean onTouch(View v, MotionEvent event) {     //长按结束

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    public_class.Send("up_end");
                }
                return true;
            }
        });

        backword.setOnClickListener(new View.OnClickListener() {          //升降电机控制页面
            public void onClick(View view) {
                public_class.Send("backword"+motor_num);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {          //升降电机控制页面
            public void onClick(View view) {
                public_class.Send("start"+motor_num);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {          //升降电机控制页面
            public void onClick(View view) {
                public_class.Send("stop"+motor_num);
            }
        });
    }
}