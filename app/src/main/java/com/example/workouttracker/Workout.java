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
import android.widget.EditText;
import android.widget.TextView;

public class Workout extends AppCompatActivity{
    SharedPreferences preferences ;
    SharedPreferences.Editor editor;
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
        preferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        editor = preferences.edit();
        show();
    }
    public void update(){
        EditText c = (EditText) findViewById(R.id.editText);
        String ex = c.getText().toString();
        editor.putString("ex1",ex);
        c = (EditText) findViewById(R.id.editText2);
        ex = c.getText().toString();
        editor.putString("ex2",ex);
        c = (EditText) findViewById(R.id.editText3);
        ex = c.getText().toString();
        editor.putString("ex3",ex);
        c = (EditText) findViewById(R.id.editText4);
        ex = c.getText().toString();
        editor.putString("ex4",ex);
        editor.commit();
    }
    public void show(){
        int myNumber;
        myNumber = preferences.getInt("c1", 0);
        TextView c = (TextView) findViewById(R.id.count1);
        c.setText(String.valueOf(myNumber));
        myNumber = preferences.getInt("c2", 0);
        c = (TextView) findViewById(R.id.count2);
        c.setText(String.valueOf(myNumber));
        myNumber = preferences.getInt("c3", 0);
        c = (TextView) findViewById(R.id.count3);
        c.setText(String.valueOf(myNumber));
        myNumber = preferences.getInt("c4", 0);
        c = (TextView) findViewById(R.id.count4);
        c.setText(String.valueOf(myNumber));
        String ex;
        ex = preferences.getString("ex1","EMPTY");
        c = (EditText) findViewById(R.id.editText);
        c.setText(ex);
        ex = preferences.getString("ex2","EMPTY");
        c = (EditText) findViewById(R.id.editText2);
        c.setText(ex);
        ex = preferences.getString("ex3","EMPTY");
        c = (EditText) findViewById(R.id.editText3);
        c.setText(ex);
        ex = preferences.getString("ex4","EMPTY");
        c = (EditText) findViewById(R.id.editText4);
        c.setText(ex);
    }
    public void Add1(View view){
        int myNumber;
        myNumber = preferences.getInt("c1", 0);
        myNumber++;
        TextView c = (TextView) findViewById(R.id.count1);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c1", myNumber);
        editor.commit();
        update();
    }
    public void Add2(View view){
        int myNumber;
        myNumber = preferences.getInt("c2", 0);
        myNumber++;
        TextView c = (TextView) findViewById(R.id.count2);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c2", myNumber);
        editor.commit();
        update();
    }
    public void Add3(View view){
        int myNumber;
        myNumber = preferences.getInt("c3", 0);
        myNumber++;
        TextView c = (TextView) findViewById(R.id.count3);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c3", myNumber);
        editor.commit();
        update();
    }
    public void Add4(View view){
        int myNumber;
        myNumber = preferences.getInt("c4", 0);
        myNumber++;
        TextView c = (TextView) findViewById(R.id.count4);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c4", myNumber);
        editor.commit();
        update();
    }
    public void Del1(View view){
        int myNumber;
        myNumber = preferences.getInt("c1", 0);
        myNumber--;
        TextView c = (TextView) findViewById(R.id.count1);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c1", myNumber);
        editor.commit();
        update();
    }
    public void Del2(View view){
        int myNumber;
        myNumber = preferences.getInt("c2", 0);
        myNumber--;
        TextView c = (TextView) findViewById(R.id.count2);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c2", myNumber);
        editor.commit();
        update();
    }
    public void Del3(View view){
        int myNumber;
        myNumber = preferences.getInt("c3", 0);
        myNumber--;
        TextView c = (TextView) findViewById(R.id.count3);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c3", myNumber);
        editor.commit();
        update();
    }
    public void Del4(View view){
        int myNumber;
        myNumber = preferences.getInt("c4", 0);
        myNumber--;
        TextView c = (TextView) findViewById(R.id.count4);
        c.setText(String.valueOf(myNumber));
        editor.putInt("c4", myNumber);
        editor.commit();
        update();
    }
    public void onBackPressed(View view) {
        EditText c = (EditText) findViewById(R.id.editText);
        String ex = c.getText().toString();
        editor.putString("ex1",ex);
        c = (EditText) findViewById(R.id.editText2);
        ex = c.getText().toString();
        editor.putString("ex2",ex);
        c = (EditText) findViewById(R.id.editText3);
        ex = c.getText().toString();
        editor.putString("ex3",ex);
        c = (EditText) findViewById(R.id.editText4);
        ex = c.getText().toString();
        editor.putString("ex4",ex);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
