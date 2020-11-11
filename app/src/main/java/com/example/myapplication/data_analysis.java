package com.example.myapplication;

import android.util.Log;

public class data_analysis {
    /*
    String str = "Test string";
    StringBuilder strBuilder = new StringBuilder(str);
    strBuilder.setCharAt(1, 'X');
    str=Builder.toString();*/
    public static final char head = 0x06;                    //常量

    public String datasend;
    public String st_start; //= "start";  //自定义协议发送
    public static final String st_get = "getstart";  //自定义协议发送
    public static final String st_get_end = "getend";  //自定义协议发送
    public static final String st_put = "putstart";  //自定义协议发送
    public static final String st_put_end = "putend";  //自定义协议发送
    public static final String st_stop = "stop";  //自定义协议发送

    public static final char agree_head = 0x55;
    public static final char agree_end = 0xaa;

    char[] st_start_byte = new char[8];


    public void data_fill(byte a)         //安卓数据填充
    {
        st_start_byte[0] = 0x01;
        st_start_byte[1] = 0x06;
        st_start_byte[2] = (char)-(256-0x9c);
        //st_start_byte[2] = 0xaa;
        st_start_byte[3] = 0x4b;

        switch (a)
        {
            case 1:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x01;
                st_start_byte[6] = 0x68;
                st_start_byte[7] = 0x08;
                /*
                st_start_byte[4] = 0x01;
                st_start_byte[5] = 0x2e;
                st_start_byte[6] = 0x56;*/
                break;
            case 2:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x02;
                st_start_byte[6] = 0x28;
                st_start_byte[7] = 0x09;
                /*
                st_start_byte[4] = 0x02;
                st_start_byte[5] = 0x6e;
                st_start_byte[6] = 0x57;*/
                break;
            case 3:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x03;
                st_start_byte[6] = (char)-(256-0xe9);
                st_start_byte[7] = (char)-(256-0xc9);
                /*
                st_start_byte[4] = 0x03;
                st_start_byte[5] = (char)-(256-0xaf);
                st_start_byte[6] = (char)-(256-0x97);*/
                break;
            case 4:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x04;
                st_start_byte[6] = (char)-(256-0xa8);
                st_start_byte[7] = 0x0b;
                /*
                st_start_byte[4] = 0x04;
                st_start_byte[5] = (char)-(256-0xee);
                st_start_byte[6] = 0x55;*/
                break;
            case 5:
                st_start_byte[4] = 0x05;
                st_start_byte[5] = 0x2f;
                st_start_byte[6] = (char)-(256-0x95);
                break;
            case 6:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x06;
                st_start_byte[6] = 0x29;
                st_start_byte[7] = (char)-(256-0xca);
                break;
            case 7:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x07;
                st_start_byte[6] = (char)-(256-0xe8);
                st_start_byte[7] = 0x0a;
                break;
            case 8:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x08;
                st_start_byte[6] = (char)-(256-0xa8);
                st_start_byte[7] = 0x0e;
                break;
            case 9:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x09;
                st_start_byte[6] = 0x69;
                st_start_byte[7] = (char)-(256-0xce);
                break;
            case 10:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x0a;
                st_start_byte[6] = 0x29;
                st_start_byte[7] = (char)-(256-0xcf);
                break;
            case 11:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x0b;
                st_start_byte[6] = (char)-(256-0xe8);
                st_start_byte[7] = 0x0f;
                break;
            case 12:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x0c;
                st_start_byte[6] = (char)-(256-0xa9);
                st_start_byte[7] = (char)-(256-0xcd);
                break;
            case 13:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x0d;
                st_start_byte[6] = 0x68;
                st_start_byte[7] = 0x0d;
                break;
            case 14:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x0e;
                st_start_byte[6] = 0x28;
                st_start_byte[7] = 0x0c;
                break;
            case 15:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x0f;
                st_start_byte[6] = (char)-(256-0xe9);
                st_start_byte[7] = (char)-(256-0xcc);
                break;
            case 16:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x10;
                st_start_byte[6] = (char)-(256-0xa8);
                st_start_byte[7] = 0x04;
                break;
            case 17:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x11;
                st_start_byte[6] = 0x69;
                st_start_byte[7] = (char)-(256-0xc4);
                break;
            case 18:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x12;
                st_start_byte[6] = 0x29;
                st_start_byte[7] = (char)-(256-0xc5);
                break;
            case 19:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x13;
                st_start_byte[6] = (char)-(256-0xe8);
                st_start_byte[7] = 0x05;
                break;
            case 20:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x14;
                st_start_byte[6] = (char)-(256-0xa0);
                st_start_byte[7] = (char)-(256-0xc7);
                break;
            case 21:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x15;
                st_start_byte[6] = 0x68;
                st_start_byte[7] = 0x07;
                break;
            case 22:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x16;
                st_start_byte[6] = 0x28;
                st_start_byte[7] = 0x06;
                break;
            case 23:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x17;
                st_start_byte[6] = (char)-(256-0xe9);
                st_start_byte[7] = (char)-(256-0xc6);
                break;
            case 24:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x18;
                st_start_byte[6] = (char)-(256-0xa9);
                st_start_byte[7] = (char)-(256-0xc2);
                break;
            case 25:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x19;
                st_start_byte[6] = 0x68;
                st_start_byte[7] = 0x02;
                break;
            case 26:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x1a;
                st_start_byte[6] = 0x28;
                st_start_byte[7] = 0x03;
                break;
            case 27:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x1b;
                st_start_byte[6] = (char)-(256-0xe9);
                st_start_byte[7] = (char)-(256-0xc3);
                break;
            case 28:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x1c;
                st_start_byte[6] = (char)-(256-0xa8);
                st_start_byte[7] = 0x01;
                break;
            case 29:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x1d;
                st_start_byte[6] = 0x69;
                st_start_byte[7] = (char)-(256-0xc1);
                break;
            case 30:
                st_start_byte[2] = 0x00;
                st_start_byte[3] = 0x0a;
                st_start_byte[4] = 0x00;
                st_start_byte[5] = 0x1e;
                st_start_byte[6] = 0x29;
                st_start_byte[7] = (char)-(256-0xc0);
                break;
        }

        StringBuilder strBuilder = new StringBuilder();
        //char ss = (char)-(256-0x9c);
        //strBuilder.append(ss);
        /*
        String ss1 = new String();
        ss1 = strBuilder.toString();

        Log.d("stsenddata1", ss1);

        */
        st_start = new String();
        for(int i = 0; i < 8; i++) {

            strBuilder.append(st_start_byte[i]);
        }
        st_start = strBuilder.toString();
        /*
        StringBuffer hex = new StringBuffer();

        for(int i = 0; i < ss1.length(); i++) {

            hex.append(Integer.toHexString(ss1.charAt(i)));
        }*/

        //Log.d("stsenddata", hex.toString());
    }

    public void data_set(int id)
    {
        switch (id)
        {
            case 1:         //回原点
                datasend = st_start;
                break;
        }
    }
}
