package com.gkftndltek.wongiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteInfoIem"); /*URL*/
                    urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=cJzPoEsdtUT5g2kVGWs65MmgIJZdGbeRKMd6yI9JJx%2F2lbbgZuxwJl5cpwos0MdE68yuXc%2F643AQ9oIGFxNA1A%3D%3D"); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode("25", "UTF-8")); /*도시코드*/
                    urlBuilder.append("&" + URLEncoder.encode("routeId","UTF-8") + "=" + URLEncoder.encode("DJB30300004", "UTF-8")); /*노선ID*/
                    //System.out.println("씨발2");
                    URL url = new URL(urlBuilder.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // System.out.println("씨바3");
                    conn.setRequestMethod("GET");
                    // System.out.println("씨바4");
                    // conn.setRequestProperty("Content-type", "application/json");
                    // System.out.println("씨바5");
                    //System.out.println("씨바6");
                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    System.out.println("씨발");
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
