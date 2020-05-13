package com.example.third;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textinput_id,textinput_pass;
    LinearLayout LinearLayout_Button1,LinearLayout_Button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textinput_id = findViewById(R.id.textinput_id); // 아이디
        textinput_pass = findViewById(R.id.textinput_pass); // 비밀번호
        LinearLayout_Button1 = findViewById(R.id.LinearLayout_Button1); // 로그인
        LinearLayout_Button2 = findViewById(R.id.LinearLayout_Button2); // first step

        // 1. 값을 가져온다.
        // 2. 클릭을 감지한다.
        // 3. 1번의 값을 다음 액티비티로 넘긴다.

        LinearLayout_Button1.setClickable(true);
        LinearLayout_Button2.setClickable(true);

        LinearLayout_Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = textinput_id.getText().toString();
                String pass = textinput_pass.getText().toString();

                if(!id.isEmpty() && !pass.isEmpty()) {
                    Intent it = new Intent(MainActivity.this, newpage.class);
                    it.putExtra("id", id);
                    it.putExtra("pass", pass);
                    startActivity(it);
                }
            }
        });
    }
}
