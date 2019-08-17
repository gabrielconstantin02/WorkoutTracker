package com.example.workouttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Health extends AppCompatActivity {
    SharedPreferences preferences ; //Save key-value data
    SharedPreferences.Editor editor;    //Edit key-value data
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health);
        preferences = getSharedPreferences("my_pref", MODE_PRIVATE);
        editor = preferences.edit();
        Thread dateThread = new Thread(new checkDate());
        dateThread.start();

    }
    public void onBackPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public class checkDate implements Runnable{
        public void run() {
            while (true) {
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                String myDate = null;
                try {
                    myDate = df.format(preferences.getString("myDate", "defaultValue"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                EditText qt = (EditText) findViewById(R.id.input_water);
                try {
                    Double quantity = Double.valueOf(qt.getText().toString());
                    if (formattedDate.equals(myDate)) {
                        TextView pqt = (TextView) findViewById(R.id.water_today);
                        //increment water intake per day and active time
                    } else {
                        preferences.edit().putString("myDate", formattedDate).apply();
                        //save this values to another category like weekly water intake
                    }
                }catch (Exception ex){
                }
            }
        }
    }
}
