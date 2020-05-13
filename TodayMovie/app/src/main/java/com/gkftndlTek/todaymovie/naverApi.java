package com.gkftndlTek.todaymovie;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class naverApi {
    public void searchNaver(final String searchObject) { // 검색어 = searchObject로 ;
        final String clientId = "JL4_Zwh4LQzOpvviGYI8";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "XT2mjUKVZP";//애플리케이션 클라이언트 시크릿값";

        // 네트워크 연결은 Thread 생성 필요
        new Thread() {
            @Override
            public void run() {
                try {
                    String text = URLEncoder.encode(searchObject, "UTF-8");

                    //https://openapi.naver.com/v1/search/movie.json
                    //https://openapi.naver.com/v1/search/movie.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&genre=1
                    //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=" + display + "&"; // json 결과
                    //String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text + "&display=10&start=1&genre=1"; // json 결과

                    String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;
                    // Json 형태로 결과값을 받아옴.

                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    con.connect();

                    int responseCode = con.getResponseCode();

                    BufferedReader br;
                    if(responseCode==200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }

                    StringBuilder searchResult = new StringBuilder();
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        searchResult.append(inputLine + "\n");
                    }

                    br.close();
                    con.disconnect();

                    String data = searchResult.toString();
                    Log.d("출력결과 : ",data);

                } catch (Exception e) { }
            }
        }.start();
    }
}

