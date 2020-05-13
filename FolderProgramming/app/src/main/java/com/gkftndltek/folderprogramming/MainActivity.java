package com.gkftndltek.folderprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gkftndltek.folderprogramming.one.Rion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rion a = new Rion();
    }
}
