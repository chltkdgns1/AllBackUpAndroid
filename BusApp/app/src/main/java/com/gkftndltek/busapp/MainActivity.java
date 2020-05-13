package com.gkftndltek.busapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView busDataTextView;
    private EditText TextInputEdit_What,TextInputEdit_How;
    private Button button;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            BusData data = (BusData)msg.obj;
            busDataTextView.setText("버스데이터 정보 : " + data.getData());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        busDataTextView = findViewById(R.id.busDataTextView);
        button = findViewById(R.id.Buttons);
        TextInputEdit_What = findViewById(R.id.TextInputEdit_What);
        TextInputEdit_How = findViewById(R.id.TextInputEdit_How);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String what = TextInputEdit_What.getText().toString();
                String how = TextInputEdit_How.getText().toString();
                if(what.isEmpty() ||  how.isEmpty()) return;
                getData(what,how);
            }
        });
    }

    void getData(String w,String h){
        final String tw  = w, th = h;
        final String key = "gCa0yyUjmoFRQ7zCUzfNHY7k9ALLNm5cVLAcR7/Cshhr2CX8nbcskiiDPgkNOpnQ6JCt5sQIMC+gOkPlCdVgIQ==";
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList"); /*URL*/
                    urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + key); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode("25", "UTF-8")); /*도시코드*/
                    urlBuilder.append("&" + URLEncoder.encode("routeNo","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*노선번호*/
                    URL url = new URL(urlBuilder.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");
                    System.out.println("Response code: " + conn.getResponseCode());
                    System.out.println("시발?");
                    BufferedReader rd;
                    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    } else {
                        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    }
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }
                    rd.close();
                    conn.disconnect();
                    System.out.println(sb.toString());
                }
                catch(Exception e){

                }
            }
        }).start();
    }
}
