package com.example.third;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginPage extends AppCompatActivity {

    TextView textview1,textview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);

        Intent it = getIntent();
        Bundle bun = it.getExtras();
        String id = bun.getString("id");
        String pass = bun.getString("pass");

        textview1.setText(id);
        textview2.setText(pass);
    }
}
