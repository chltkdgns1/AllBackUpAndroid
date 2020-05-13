package com.gkftndlTek.todaymovie;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MovityPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String s = "인셉션";
        getMovie(s);
    }

    public void getMovie(final String searchObject) { // 검색어 = searchObject로 ;
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

                        mAdapter = new MyAdapter(moviedata,MovityPage.this);
                        recyclerView.setAdapter(mAdapter);


                    } catch (Exception e) { }
                }
            }.start();

        }
        /*
        String url ="https://newsapi.org/v2/top-headlines?count" +
                "ry=kr&apiKey=56a4fc1947cf4a85943f83c8ddbf89cf";

        List<Newsdata> news = new ArrayList<>();

        JSONObject jobj = new JSONObject(response);
        JSONArray obj_arr = jobj.getJSONArray("articles");

        for(int i = 0, k = obj_arr.length() ; i < k ; i++){

            Newsdata newsdata = new Newsdata();
            JSONObject j_obj = obj_arr.getJSONObject(i);
            newsdata.setTitle(j_obj.getString("title"));
            newsdata.setUrlimage(j_obj.getString("urlToImage"));
            newsdata.setContent(j_obj.getString("description"));
            news.add(newsdata);

        }
         */
}
