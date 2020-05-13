package com.gkftndltek.soundtest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SoundPool sp;
    private int soundid;
    private AudioManager am;
    private Button Button_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button_sound = findViewById(R.id.Button_sound);

        sp = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundid = sp.load(this,R.raw.kaotalk,1);
        Button_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"dasda",Toast.LENGTH_LONG).show();
                sp.play(soundid,1,1,0,0,1);
            }
        });

    }
}
