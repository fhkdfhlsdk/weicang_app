package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.myapplication.public_class;
import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

public class controlActivity extends AppCompatActivity {
    //public public_class public_class ;
    //InetSocketAddress tcp_addresss;
    private java.net.InetSocketAddress isa = null;
    public static final int recv_text = 1;
    private static final int TCPTIMEOUT = 100;
    private boolean socket_status = false;

    data_analysis data_analysis_new;

    EditText ip_st;
    EditText port_st;
    EditText get_id;
    EditText put_id;
    TextView show_text;
    public ProgressDialog pd;// = new ProgressDialog(controlActivity.this);

    SerialPortFinder mSerialPortFinder;
    SerialPort  mSerialPort;
    //TextView show_text = (TextView) findViewById(R.id.textView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 详见StrictMode文档，防止出现 android.os.NetworkOnMainThreadException 异常
        /*
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        socket_create();    //tcp连接
        uart_open();            //串口打开连接

        Button start = (Button) findViewById(R.id.button);  //回原位
        Button stop = (Button) findViewById(R.id.button2);  //停止
        Button hand = (Button) findViewById(R.id.button7);  //手动

        final Button plc_connect = (Button) findViewById(R.id.button6);   //plc连接

        Button get_start = (Button) findViewById(R.id.button8);  //取货
        Button get_end = (Button) findViewById(R.id.button10);  //取货完成
        Button put_start = (Button) findViewById(R.id.button4);  //存货
        Button put_end = (Button) findViewById(R.id.button11);  //存货完成
        get_id  = (EditText) findViewById(R.id.editTextNumber);
        put_id  = (EditText) findViewById(R.id.editTextNumber2);

        show_text = (TextView) findViewById(R.id.textView);
        ip_st = (EditText) findViewById(R.id.editTextTextPersonName2);
        port_st = (EditText) findViewById(R.id.editTextTextPersonName3);

        pd = new ProgressDialog(controlActivity.this);

        data_analysis_new = new data_analysis();

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*
                //Log.d("start", "onClick: ");
                if(socket_status) {
                    data_analysis_new.data_fill((byte)1);
                    Send(data_analysis_new.st_start);
                    /*
                    show_text.setText(" ");     //清空
                    show_text.append("回原位中");
                    //view_change();
                    //pd.show(controlActivity.this, "等待运行完毕", "wait…");
                    pd.show();
                }
                else
                {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                }*/
                data_analysis_new.data_fill((byte)1);
                uart_send(data_analysis_new.st_start);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*
                //Log.d("get", "onClick: ");
                if(socket_status) {

                    data_analysis_new.data_fill((byte)2);
                    uart_send(data_analysis_new.st_start);
                    //Send(data_analysis.st_stop);
                   // show_text.setText(" ");     //清空
                    //show_text.append("停止");
                   // pd.show();
                    //socket_close();
                }
                else
                {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                }*/
                data_analysis_new.data_fill((byte)2);
                uart_send(data_analysis_new.st_start);
            }
        });

        //进入单个点击控制界面
        hand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(controlActivity.this, hand_control.class));
                /*
                Log.d("get", "onClick: ");
                if (public_class.socket.isConnected()) {
                    startActivity(new Intent(controlActivity.this, hand_control.class));
                } else {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        get_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                /*
                if(!socket_status) {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //Log.d("get", "onClick: ");
                show_text.setText(" ");     //清空
                show_text.append("取货中");
                if(TextUtils.isEmpty(get_id.getText())) //如果当前输入为空
                {
                    Toast.makeText(controlActivity.this, "请输入取货id", Toast.LENGTH_SHORT).show();
                }
                else {
                    int get_id_int = new Integer(get_id.getText().toString());  //获取取货id
                    String send_string = get_id.getText().toString();
                    if ((get_id_int < 13) && (get_id_int > 0))       //判断大小
                    {
                        data_analysis_new.data_fill((byte)(get_id_int+6));
                        uart_send(data_analysis_new.st_start);
                        /*
                        send_string = data_analysis.st_get + get_id.getText().toString();
                        Send(send_string);
                        pd.show();*/
                    } else {
                        Toast.makeText(controlActivity.this, "取货id错误,请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        get_end.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                data_analysis_new.data_fill((byte)4);
                uart_send(data_analysis_new.st_start);
                /*
                //Log.d("get", "onClick: ");
                if(!socket_status) {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                    return;
                }
                Send(data_analysis.st_get_end);
                show_text.setText(" ");     //清空
                show_text.append("取货完成");
                //pd.show();*/
            }
        });

        put_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Log.d("get", "onClick: ");
                /*
                if(!socket_status) {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                show_text.setText(" ");     //清空
                show_text.append("存货中");
                if(TextUtils.isEmpty(put_id.getText())) //如果当前输入为空
                {
                    Toast.makeText(controlActivity.this, "请输入存货id", Toast.LENGTH_SHORT).show();
                }
                else {
                    int put_id_int = new Integer(put_id.getText().toString());  //获取取货id
                    String send_string;
                    if ((put_id_int < 13) && (put_id_int > 0))       //判断大小
                    {
                        data_analysis_new.data_fill((byte)(put_id_int+18));
                        uart_send(data_analysis_new.st_start);
                        /*
                        send_string = data_analysis.st_put + put_id.getText().toString();
                        Send(send_string);
                        pd.show();*/
                    } else {
                        Toast.makeText(controlActivity.this, "存货id错误,请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        put_end.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                data_analysis_new.data_fill((byte)6);
                uart_send(data_analysis_new.st_start);
                /*
                if(!socket_status) {
                    Toast.makeText(controlActivity.this, "请连接plc", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Log.d("get", "onClick: ");
                Send(data_analysis.st_put_end);
                show_text.setText(" ");     //清空
                show_text.append("放货完成");
                //pd.show();*/
            }
        });

        plc_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("pcl", "connect");

                String ip;
                String port;
                ip = ip_st.getText().toString();
                port = port_st.getText().toString();
                int port_num = new Integer(port);

                if (isIP(ip)) {
                    try {

                        isa =  new InetSocketAddress(ip, port_num);//安卓的本机地址 相当于pc的127.0.0.1

                        public_class.socket .connect(isa, TCPTIMEOUT);                                 //加入延时
                        //public_class.socket .connect(isa);

                        if (public_class.socket .isConnected()) {
                            socket_status =true;
                            Log.d("socket1", "public_class.socket  connect");
                            ip_st.setEnabled(false);                        //锁定文本框
                            port_st.setEnabled(false);
                            show_text.setText(" ");     //清空
                            show_text.append("连接plc成功");
                        } else {
                            public_class.socket .close();
                            show_text.append("连接plc失败");
                            socket_status = false;
                            Log.d("socket1", "public_class.socket  disconnect");
                            Toast.makeText(controlActivity.this, "tcp连接失败", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    socket_status = false;
                    Toast.makeText(controlActivity.this, "ip输入不合法，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Recv();
                }
            }
        }).start();
    }

    public void socket_create(){
        try{
            public_class.socket  = new Socket();      //创建套接字
            //isa = new InetSocketAddress("10.0.0.2",5566);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Send(String s) {
        try {

            // 发送数据给服务端
            //Object os=new Object();     //反序列化接口
            //ObjectOutputStream outputStream=new ObjectOutputStream(public_class.socket .getOutputStream());
            //outputStream.writeObject("123");

            OutputStream outsend = public_class.socket .getOutputStream();
            //DataOutputStream dos = new DataOutputStream(outsend);     //使用wtf会导致在数据头添加两个字节的数据长度
            BufferedOutputStream dos = new BufferedOutputStream(outsend);//这个不会添加字节
            //dos.writeUTF("123");

            int send_data;
            for(int i = 0; i < s.length(); i++) {
                send_data = (int)s.charAt(i)&0xff;
                dos.write(send_data);
            }
            /*
            for(int i = 0; i < s.length(); i++) {
                StringBuffer hex = new StringBuffer();
                String m = new String(Integer.toString(i));

                //new_ch = Integer.toHexString(s.charAt(i)&0xff);


                hex.append(Integer.toHexString(s.charAt(i)&0xff));



                Log.d("sendmsg"+m, hex.toString());

                //hex.delete(0,1);
            }*/

            //Log.d("stsenddata", hex.toString());

            //dos.write(s.getBytes("UTF-8"));
            //dos.write(s.getBytes("gbk"));
            //dos.write(s.getBytes("ASCII"));

            dos.flush();
            //dos.close();

            //OutputStream outputStream = new ObjectOutputStream(public_class.socket .getOutputStream());

           // outputStream.write(s.getBytes("utf-8"));
            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

            // 步骤3：发送数据到服务端 
            //outputStream.flush();       //需要清除
           // outputStream.close();

           // Log.d(s.toString(), "Send: ");
            //public_class.socket .close();
            // public_class.socket .shutdownOutput();
            /*
            // 等待服务器发送数据，读取数据（超时异常）
            DataInputStream br = new DataInputStream(public_class.socket .getInputStream());
            byte[] b = new byte[1024];
            int length = br.read(b);
            String Msg = new String(b, 0, length, "gb2312");
            System.out.println(Msg + "    接收到服务器的数据");
        } catch (UnknownHostException e) {
            System.out.println("UnknownHost  来自服务器的数据");
            e.printStackTrace();*/
        } catch (IOException e) {
            System.out.println("IOException   来自服务器的数据");
            e.printStackTrace();
        }
    }

    private String Recv() {
        try {
            DataInputStream br = new DataInputStream(public_class.socket .getInputStream());
            byte[] b = new byte[1024];
            int length = br.read(b);
            String st = new String(b, 0, length, "gb2312");
            //Log.d("thead", st);

            Message msg = new Message();
            msg.what = recv_text;
            handler.sendMessage(msg);           //发送
            return "recv";
        } catch (IOException e) {
            System.out.println("IOException   来自服务器的数据");
            e.printStackTrace();
            return "error";
        }
    }

    private void socket_close() {
        try {
            public_class.socket .close();
        }catch (IOException e) {
            System.out.println("tcp close");
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case recv_text:
                    //show_text.clearComposingText();
                    show_text.setText(" ");     //清空
                    show_text.append("received");
                    pd.dismiss();
                    break;
            }
        }
    };

    public void view_change(){
        Intent intent = new Intent();
        intent.setClass(controlActivity.this,WaitingActivity.class);
        startActivity(intent);
    }

    public boolean isIP(String addr)
    {
        if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
        {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        //============对之前的ip判断的bug在进行判断
        if (ipAddress==true){
            String ips[] = addr.split("\\.");
            if(ips.length==4){
                try{
                    for(String ip : ips){
                        if(Integer.parseInt(ip)<0||Integer.parseInt(ip)>255){
                            return false;
                        }
                    }
                }catch (Exception e){
                    return false;
                }
                return true;
            }else{
                return false;
            }
        }
        return ipAddress;
    }

    public void uart_open()
    {
        mSerialPortFinder = new SerialPortFinder();

        // 得到所有设备文件地址的数组
        // 实际上该操作并不需要，这里只是示例打印出所有的设备信息
        String[] entryValues = mSerialPortFinder.getAllDevicesPath();

        try {
            // 打开/dev/ttyUSB0路径设备的串口
            mSerialPort = new SerialPort(new File("/dev/ttymxc5"), 9600, 0);

        } catch (IOException e) {
            Log.d("uart", "error");
            System.out.println("找不到该设备文件");

        }
    }

    public void uart_send(String s) {
        //String content = "Hello World";
        //byte[] bytes = content.getBytes();
        int send_data;

        OutputStream out = mSerialPort.getOutputStream();
        // 写入数据
        try {
            for(int i = 0; i < s.length(); i++) {
                send_data = (int) s.charAt(i) & 0xff;
                out.write(send_data);
            }
            out.flush();
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
