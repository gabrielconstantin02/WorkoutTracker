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

public class Health extends AppCompatActivity {
    SharedPreferences preferences ; //Save key-value data
    SharedPreferences.Editor editor;    //Edit key-value data
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health);
        preferences = getSharedPreferences("my_pref", MODE_PRIVATE);
        editor = preferences.edit();
        TextView mqt=(TextView) findViewById(R.id.water_month);
        float water_month=preferences.getFloat("water month",0);
        mqt.setText(String.valueOf(water_month));
        TextView pqt = (TextView) findViewById(R.id.water_today);
        float water_today=preferences.getFloat("water today",0);
        pqt.setText(String.valueOf(water_today));
    }
    public void onBackPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
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
                    Log.d("water",String.valueOf(quantity));
                    TextView pqt = (TextView) findViewById(R.id.water_today);
                    float water_today=preferences.getFloat("water today",0);
                    //pqt.setText(String.valueOf(water));
                    if (formattedDate.equals(myDate)||myDate.equals("16-Aug-2019")) {
                        //Float water_today=Float.valueOf(pqt.getText().toString());
                        Log.d("water",String.valueOf(water_today));
                        water_today=quantity+water_today;
                        editor.putFloat("water today",water_today);
                        pqt.setText(String.valueOf(water_today));
                        //increment water intake per day and active time
                    } else {
                        editor.putString("myDate", formattedDate);
                        editor.commit();
                        //save this values to another category like weekly water intake
                    }
                    TextView mqt=(TextView) findViewById(R.id.water_month);
                    float water_month=preferences.getFloat("water month",0);
                    Log.d("month",String.valueOf(water_month));
                    Float total=water_month+quantity;
                    mqt.setText(String.valueOf(total));
                    editor.putFloat("water month",total);
                    editor.commit();
                    qt.setText(null);
                }catch (Exception ex){
                }
            }
        }
