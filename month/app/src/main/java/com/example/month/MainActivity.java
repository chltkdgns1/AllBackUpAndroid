package com.example.month;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    MaterialCalendarView mat;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mat= (MaterialCalendarView)findViewById(R.id.calendarView);

        mat.setOnDateChangedListener();

        mat.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator());


    }
}
