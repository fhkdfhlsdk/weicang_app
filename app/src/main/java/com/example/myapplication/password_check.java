package com.example.myapplication;

import android.widget.Toast;

public class password_check {
    boolean check_result(String name,String password)
    {
        if(name.isEmpty()||password.isEmpty())        //获取为空
        {
            //makeText(MainActivity.this, "neutral: " + which, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this,"输入错误，请重新输入",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(name.equals("songkan")&&password.equals("123"))        //账号输入成功
        {
            //Toast.makeText(MainActivity.this,"用户输入成功",Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            //Toast.makeText(MainActivity.this,"输入错误，请重新输入",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
