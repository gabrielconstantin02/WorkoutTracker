package com.example.workouttracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Workout extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        /*SharedPreferences preferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int myNumber;
        myNumber = preferences.getInt("myNumber", 0);
        myNumber++;
        editor.putInt("myNumber", myNumber);
        editor.commit();
        TextView errorView = (TextView) findViewById(R.id.title);
        errorView.setText(String.valueOf(myNumber));*/
    }

    public void onBackPressed(View view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
    }
}
