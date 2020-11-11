package com.example.myapplication;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class public_class {
    public static Socket socket;

    public static void Send(String s) {
        try {
            OutputStream outsend = socket.getOutputStream();
            //DataOutputStream dos = new DataOutputStream(outsend);     //使用wtf会导致在数据头添加两个字节的数据长度
            BufferedOutputStream dos = new BufferedOutputStream(outsend);//这个不会添加字节
            //dos.writeUTF("123");
            dos.write(s.getBytes("UTF-8"));

            dos.flush();

            Log.d(s.toString(), "Send: ");
        } catch (IOException e) {
            System.out.println("IOException   来自服务器的数据");
            e.printStackTrace();
        }
    };

    public static void printlog(){

    };
}
