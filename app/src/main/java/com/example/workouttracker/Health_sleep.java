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

public class Health_sleep extends AppCompatActivity {
    SharedPreferences preferences ; //Save key-value data
    SharedPreferences.Editor editor;    //Edit key-value data
    int day=1;
    float total_quantity=0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_sleep);
        preferences = getSharedPreferences("my_pref", MODE_PRIVATE);
        editor = preferences.edit();
        day=preferences.getInt("sleep day",1);
        total_quantity=preferences.getFloat("sleep per day",0);
        TextView mqt=(TextView) findViewById(R.id.sleep_month);
        float sleep_month=preferences.getFloat("sleep month",0);
        mqt.setText("sleep time this month:"+sleep_month);
        TextView pqt = (TextView) findViewById(R.id.sleep_today);
        float sleep_today=preferences.getFloat("sleep today",0);
        pqt.setText("sleep time today:"+sleep_today);
        TextView tqt=(TextView) findViewById(R.id.sleep_per_day);
        tqt.setText("sleep time/day:"+total_quantity/day);
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
        String myDateSleep = null;
        Log.d("CurrentDate",formattedDate);
        try {
            myDateSleep = preferences.getString("myDateSleep", "16-Aug-2019");
            Log.d("SavedDate",myDateSleep);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        EditText qt = (EditText) findViewById(R.id.input_sleep);
        try {
            Float quantity = Float.valueOf(qt.getText().toString());
            total_quantity+=quantity;
            Log.d("sleep",String.valueOf(quantity));
            TextView pqt = (TextView) findViewById(R.id.sleep_today);
            float sleep_today=preferences.getFloat("sleep today",0);
            //pqt.setText(String.valueOf(sleep));
            if (formattedDate.equals(myDateSleep)||myDateSleep.equals("16-Aug-2019")) {
                //Float sleep_today=Float.valueOf(pqt.getText().toString());
                Log.d("sleep",String.valueOf(sleep_today));
                sleep_today=quantity+sleep_today;
                editor.putFloat("sleep today",sleep_today);
                pqt.setText("sleep time today:"+String.valueOf(sleep_today));
                //increment sleep intake per day and active time
            } else {
                if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
                    editor.putFloat("sleep month", quantity);
                    editor.commit();
                }
                editor.putString("myDateSleep", formattedDate);
                editor.commit();
                pqt.setText("sleep time today:"+String.valueOf(quantity));
                editor.putFloat("sleep today",quantity);
                editor.commit();
                day++;
                editor.putInt("sleep day",day);
                editor.commit();
                //save this values to another category like weekly sleep intake
            }
            TextView mqt=(TextView) findViewById(R.id.sleep_month);
            float sleep_month=preferences.getFloat("sleep month",0);
            Log.d("month",String.valueOf(sleep_month));
            Float total=sleep_month+quantity;
            mqt.setText("sleep time this month:"+String.valueOf(total));
            editor.putFloat("sleep month",total);
            editor.commit();
            TextView tqt=(TextView) findViewById(R.id.sleep_per_day);
            editor.putFloat("sleep per day",total_quantity);
            tqt.setText("sleep time/day:"+String.valueOf(total_quantity/day));
            qt.setText(null);
        }catch (Exception ex){
        }
    }
}
