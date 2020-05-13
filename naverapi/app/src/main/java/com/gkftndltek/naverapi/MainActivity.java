package com.gkftndltek.naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView edtHtml;
    final int CONN_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String naverHtml = getNaverHtml();

        edtHtml = (TextView)this.findViewById(R.id.text);
        //edtHtml.setText(naverHtml);

        new Thread() {
            public void run() {
                String naverHtml = getNaverHtml();

                Bundle bun = new Bundle();
                bun.putString("HTML_DATA", naverHtml);

                Message msg = handler.obtainMessage();
                msg.setData(bun);
                handler.sendMessage(msg);
            }
        }.start();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bun = msg.getData();
            String naverHtml = bun.getString("HTML_DATA");
            edtHtml.setText(naverHtml);
        }
    };

    private String getNaverHtml(){
        String naverHtml = "";

        HttpURLConnection con = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try{
            URL url = new URL("http://www.naver.com");
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(CONN_TIME);
            con.setReadTimeout(CONN_TIME);

            isr = new InputStreamReader(con.getInputStream());
            br = new BufferedReader(isr);

            String str = null;
            while ((str = br.readLine()) != null) {
                naverHtml += str + "\n";
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(con != null){
                try{con.disconnect();}catch(Exception e){}
            }

            if(isr != null){
                try{isr.close();}catch(Exception e){}
            }

            if(br != null){
                try{br.close();}catch(Exception e){}
            }
        }
        return naverHtml;
    }
}
