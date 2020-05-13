package com.gkftndltek.activity_testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button main_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,TestActivity.class);
                startActivity(it);

                Log.d("야이야이야이야이ㅇ얄라리얄라얄ㅇ라","dsadsadada");
            }
        });
        Log.d("야이야이야이야이ㅇ얄라리얄라얄ㅇ라","dsadsadada");
    }
}
