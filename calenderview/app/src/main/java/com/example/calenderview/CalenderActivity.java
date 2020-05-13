package com.example.calenderview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalenderActivity extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_calender);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + " / " +  (month + 1) + " / " + dayOfMonth;
                Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();

                Intent it = new Intent(CalenderActivity.this,MainActivity.class);
                it.putExtra("date" , date);
                startActivity(it);
            }
        });
    }
}
