package com.gkftndltek.toolbar_two;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar_action, menu);
        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search :
                ((TextView)findViewById(R.id.textView)).setText("SEARCH") ;
                return true ;
            case R.id.action_account :
                ((TextView)findViewById(R.id.textView)).setText("ACCOUNT") ;
                return true ;
            case R.id.action_settings :
                ((TextView)findViewById(R.id.textView)).setText("SETTINGS") ;
                return true ;
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }

 */
}
