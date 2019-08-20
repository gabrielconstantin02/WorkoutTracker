package com.example.workouttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Health_active extends AppCompatActivity {
    SharedPreferences preferences ; //Save key-value data
    SharedPreferences.Editor editor;    //Edit key-value data
    int day=1;
    float total_quantity=0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_active);
        preferences = getSharedPreferences("my_pref", MODE_PRIVATE);
        editor = preferences.edit();
        day=preferences.getInt("active day",1);
        total_quantity=preferences.getFloat("active per day",0);
        TextView mqt=(TextView) findViewById(R.id.active_month);
        float active_month=preferences.getFloat("active month",0);
        mqt.setText("active time this month:"+active_month);
        TextView pqt = (TextView) findViewById(R.id.active_today);
        float active_today=preferences.getFloat("active today",0);
        pqt.setText("active time today:"+active_today);
        TextView tqt=(TextView) findViewById(R.id.active_per_day);
        tqt.setText("active time/day:"+total_quantity/day);
    }
    public void onBackPressed(View view) {
        Intent intent = new Intent(this, Health.class);
        startActivity(intent);
        finish();
    }
    public void onDone(View view)  {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        String myDateActive = null;
        Log.d("CurrentDate",formattedDate);
        try {
            myDateActive = preferences.getString("myDateActive", "16-Aug-2019");
            Log.d("SavedDate",myDateActive);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        EditText qt = (EditText) findViewById(R.id.input_active);
        try {
            Float quantity = Float.valueOf(qt.getText().toString());
            total_quantity+=quantity;
            Log.d("active",String.valueOf(quantity));
            TextView pqt = (TextView) findViewById(R.id.active_today);
            float active_today=preferences.getFloat("active today",0);
            //pqt.setText(String.valueOf(active));
            if (formattedDate.equals(myDateActive)||myDateActive.equals("16-Aug-2019")) {
                //Float active_today=Float.valueOf(pqt.getText().toString());
                Log.d("active",String.valueOf(active_today));
                active_today=quantity+active_today;
                editor.putFloat("active today",active_today);
                pqt.setText("active time today:"+String.valueOf(active_today));
                //increment active intake per day and active time
            } else {
                if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
                    editor.putFloat("active month", quantity);
                    editor.commit();
                }
                editor.putString("myDateActive", formattedDate);
                editor.commit();
                pqt.setText("active time today:"+String.valueOf(quantity));
                editor.putFloat("active today",quantity);
                editor.commit();
                day++;
                editor.putInt("active day",day);
                editor.commit();
                //save this values to another category like weekly active intake
            }
            TextView mqt=(TextView) findViewById(R.id.active_month);
            float active_month=preferences.getFloat("active month",0);
            Log.d("month",String.valueOf(active_month));
            Float total=active_month+quantity;
            mqt.setText("active time this month:"+String.valueOf(total));
            editor.putFloat("active month",total);
            editor.commit();
            TextView tqt=(TextView) findViewById(R.id.active_per_day);
            editor.putFloat("active per day",total_quantity);
            tqt.setText("active time/day:"+String.valueOf(total_quantity/day));
            qt.setText(null);
        }catch (Exception ex){
        }
    }
}
