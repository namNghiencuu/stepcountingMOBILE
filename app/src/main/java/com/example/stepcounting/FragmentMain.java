package com.example.stepcounting;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class FragmentMain extends Fragment {

    View view;
    public FragmentMain() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
//        mydb = new Database(this );
//        btnViewAll = (Button)container.findViewById(R.id.button2);
//        viewAll();
//        Cursor res = mydb.getAllData();

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

//        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
//                .range(startDate, endDate)
//                .datesNumberOnScreen(5)
//                .build();

//        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
//            @Override
//            public void onDateSelected(Calendar date, int position) {
//                //do something
//            }
//        });
        return view;
    }

    Database mydb;
//    Button btnViewAll;


//    public void viewAll(){
//        btnViewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = mydb.getAllData();
//                        if (res.getCount() == 0 ){
//                            showMessage("Error", "Nothing to found");
//                            return;
//                        }
//
//                        StringBuffer buffer =  new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("ID" + res.getString(0) + "\n");
//                            buffer.append("Step" + res.getString(1) + "\n");
//                        }
//                        showMessage("Data", buffer.toString());
//                    }
//                }
//        );
//    }
    public void showMessage( String title, String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();

    }
}
