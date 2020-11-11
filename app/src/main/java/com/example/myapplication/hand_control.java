package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.public_class;
import com.example.myapplication.controlActivity;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class hand_control extends AppCompatActivity {
    //public_class public_class;        //在不同类之间，非静态方法需要通过对象才能调用非静态方法。
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_hand_control);

       Button up_and_down = (Button) findViewById(R.id.button3);  //升降
       Button for_and_back = (Button) findViewById(R.id.button9);  //行走
       Button recycle = (Button) findViewById(R.id.button12);  //旋转
       Button get_package = (Button) findViewById(R.id.button13);  //取货
       /*
       if (public_class.socket.isConnected()) {
           //Log.d("hello","onCreate");
          // public_class.Send("hello");
       } else {
           //Log.d("hello","onMiss");
           // Send("world");
       }*/
       //controlActivity.Send("hello");

       up_and_down.setOnClickListener(new View.OnClickListener() {          //进入升降电机控制页面
           public void onClick(View view) {
               Intent intent = new Intent(hand_control.this,Motor1.class);
               intent.putExtra("motor_num","1");
               startActivity(intent);
           }
       });

       for_and_back.setOnClickListener(new View.OnClickListener() {          //进入行走电机控制页面
           public void onClick(View view) {
               Intent intent = new Intent(hand_control.this,Motor1.class);
               intent.putExtra("motor_num","2");
               startActivity(intent);
           }
       });

       recycle.setOnClickListener(new View.OnClickListener() {          //进入旋转电机控制页面
           public void onClick(View view) {
               Intent intent = new Intent(hand_control.this,Motor1.class);
               intent.putExtra("motor_num","3");
               startActivity(intent);
           }
       });

       get_package.setOnClickListener(new View.OnClickListener() {          //进入伸缩电机控制页面
           public void onClick(View view) {
               //startActivity(new Intent(hand_control.this,Motor1.class));
               Intent intent = new Intent(hand_control.this,Motor1.class);
               intent.putExtra("motor_num","4");
               startActivity(intent);
           }
       });
   }
}