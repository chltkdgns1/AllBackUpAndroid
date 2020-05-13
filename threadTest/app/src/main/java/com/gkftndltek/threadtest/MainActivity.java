package com.gkftndltek.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("씨Fire");
                if (msg.what == 1) {
                    System.out.println("시발 누가 날 불렀누");
                    System.out.println("핸드러 메시지 : " + msg.arg1);
                }
            }
        };

        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                System.out.println("^^ㅣ발");
                Message msg = Message.obtain();
                msg.what = 1;
                msg.arg1 = 1;
                handler.sendMessage(msg);
                Looper.loop();
            }
        };

        thread.start();

    }


}
