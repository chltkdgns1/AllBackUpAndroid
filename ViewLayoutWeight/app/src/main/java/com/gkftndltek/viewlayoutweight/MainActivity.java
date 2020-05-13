package com.gkftndltek.viewlayoutweight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView hbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hbar = (TextView) findViewById(R.id.hbar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rnd = new Random(); // 랜덤 수를 얻기 위한 Random 객체 생성

                // 가중치(layout_weight) 동적으로 설정
                LinearLayout.LayoutParams params
                        = (LinearLayout.LayoutParams) hbar.getLayoutParams();
                params.weight = rnd.nextInt(100) + 1;
                hbar.setLayoutParams(params);
                hbar.setText(((int) params.weight) + "%");
                // TextView 에 현재 가중치값 출력
                Log.d("AAAAAAA", "weight:" + params.weight);
            }
        });
    }
}