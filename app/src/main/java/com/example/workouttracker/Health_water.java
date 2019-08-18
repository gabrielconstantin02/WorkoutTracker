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

public class Health_water extends AppCompatActivity {
    SharedPreferences preferences ; //Save key-value data
    SharedPreferences.Editor editor;    //Edit key-value data
    int day=1;
    float total_quantity=0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_water);
        preferences = getSharedPreferences("my_pref", MODE_PRIVATE);
        editor = preferences.edit();
        total_quantity=preferences.getFloat("water per day",0);
        TextView mqt=(TextView) findViewById(R.id.water_month);
        float water_month=preferences.getFloat("water month",0);
        mqt.setText("water consumed this month:"+water_month);
        TextView pqt = (TextView) findViewById(R.id.water_today);
        float water_today=preferences.getFloat("water today",0);
        pqt.setText("Water consumed today:"+water_today);
        TextView tqt=(TextView) findViewById(R.id.water_per_day);
        tqt.setText("Water consumed/day:"+total_quantity/day);
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
        String myDate = null;
        Log.d("CurrentDate",formattedDate);
        try {
            myDate = preferences.getString("myDate", "16-Aug-2019");
            Log.d("SavedDate",myDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        EditText qt = (EditText) findViewById(R.id.input_water);
        try {
            Float quantity = Float.valueOf(qt.getText().toString());
            total_quantity+=quantity;
            Log.d("water",String.valueOf(quantity));
            TextView pqt = (TextView) findViewById(R.id.water_today);
            float water_today=preferences.getFloat("water today",0);
            //pqt.setText(String.valueOf(water));
            if (formattedDate.equals(myDate)||myDate.equals("16-Aug-2019")) {
                //Float water_today=Float.valueOf(pqt.getText().toString());
                Log.d("water",String.valueOf(water_today));
                water_today=quantity+water_today;
                editor.putFloat("water today",water_today);
                pqt.setText("Water consumed today:"+String.valueOf(water_today));
                //increment water intake per day and active time
            } else {
                if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
                    editor.putFloat("water month", quantity);
                    editor.commit();
                }
                editor.putString("myDate", formattedDate);
                editor.commit();
                pqt.setText("Water consumed today:"+String.valueOf(quantity));
                editor.putFloat("water today",quantity);
                editor.commit();
                day++;
                //save this values to another category like weekly water intake
            }
            TextView mqt=(TextView) findViewById(R.id.water_month);
            float water_month=preferences.getFloat("water month",0);
            Log.d("month",String.valueOf(water_month));
            Float total=water_month+quantity;
            mqt.setText("Water consumed this month:"+String.valueOf(total));
            editor.putFloat("water month",total);
            editor.commit();
            TextView tqt=(TextView) findViewById(R.id.water_per_day);
            editor.putFloat("water per day",total_quantity);
            tqt.setText("Water consumed/day:"+String.valueOf(total_quantity/day));
            qt.setText(null);
        }catch (Exception ex){
        }
    }
}
