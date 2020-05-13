package com.gkftndltek.locationsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovie("엽기떡볶이");
    }

    public void getMovie(final String searchObject) { // 검색어 = searchObject로 ;
        final String clientId = "sZrrMBE3wEDN68YUHd8j";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "UDZOegJFEG";//애플리케이션 클라이언트 시크릿값";

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

                    String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text;


                    // String apiURL = "https://openapi.naver.com/v1/search/webkr.json?query=" + text;
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
                    System.out.println(data);



                    //   Log.d("하이하이하이하이하이하이하이하이하이하이",data);
/*
                    List<MovieData> moviedata  = new ArrayList<>();
                    JSONObject jobj  = new JSONObject(data);
                    JSONArray obj_arr = jobj.getJSONArray("items");

                    for(int i = 0,k = obj_arr.length(); i < k ; i++ ){
                        MovieData movie_data = new MovieData();
                        JSONObject movie_obj = obj_arr.getJSONObject(i);
                        movie_data.setTitle(movie_obj.getString("title"));
                        movie_data.setUrlimage(movie_obj.getString("image"));
                        movie_data.setActor(movie_obj.getString("actor"));
                        movie_data.setDirector(movie_obj.getString("director"));
                        movie_data.setPubDate(movie_obj.getString("pubDate"));
                        movie_data.setSubtitle(movie_obj.getString("subtitle"));
                        movie_data.setUserRationg(movie_obj.getString("userRating"));
                        moviedata.add(movie_data);
                    }

 */
                } catch (Exception e) { }
            }
        }.start();

    }
}
