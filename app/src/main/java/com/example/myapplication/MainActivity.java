package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.example.myapplication.controlActivity;

public class MainActivity extends AppCompatActivity {

    public String user_st;
    public String password_st;
    password_check password_check_on = new password_check();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sign_user = (Button)findViewById(R.id.button5);
        final EditText user = (EditText)findViewById(R.id.editTextTextPersonName);
        final EditText password = (EditText)findViewById(R.id.editTextTextPassword);


        sign_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("button5","onClick: ");
                user_st = user.getText().toString();
                password_st = password.getText().toString();
                startActivity(new Intent(MainActivity.this,controlActivity.class)); //先测试
                if(password_check_on.check_result(user_st,password_st))
                {
                    Toast.makeText(MainActivity.this,"用户输入成功",Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(MainActivity.this,controlActivity.class);

                    startActivity(new Intent(MainActivity.this,controlActivity.class));         //这里找不到this 引以为戒
                }
                else
                {
                    Toast.makeText(MainActivity.this,"输入错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setText("");
            }
        });

    }
}