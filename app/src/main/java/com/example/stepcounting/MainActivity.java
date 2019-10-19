package com.example.stepcounting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class MainActivity extends AppCompatActivity {
    Database mydb;
    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new Database(this );
        btnViewAll = (Button)findViewById(R.id.button2);
        viewAll();
        Cursor res = mydb.getAllData();
        Button Stat = (Button) findViewById(R.id.start_statistics_button);
        Stat.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                StatisticActiity();
            }
        }
        );

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });
    }
    public void viewAll(){
        btnViewAll.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0 ){
                            showMessage("Error", "Nothing to found");
                            return;
                        }

                        StringBuffer buffer =  new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID" + res.getString(0) + "\n");
                            buffer.append("Step" + res.getString(1) + "\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void showMessage( String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    private void StatisticActiity() {
        Intent intent = new Intent(this, Statistic.class);
        startActivity(intent);
    }
}
