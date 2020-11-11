package com.example.moneygement.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import com.example.moneygement.R;
import com.example.moneygement.controller.MainActivity;

import java.util.Calendar;

public class DispCalenderHouseholdAccountBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_calender_household_account_book);
        //CalenderViewにリスナーを設定
        ((CalendarView) findViewById(R.id.calendarview)).setOnDateChangeListener(listener);
    }

    CalendarView.OnDateChangeListener listener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            Integer expenseYear = year;
            Integer expenseMonth = month+1;
            Integer expenseDay = dayOfMonth;

            //インテント生成
            Intent intent = new Intent
                    (DispCalenderHouseholdAccountBookActivity.this,InputHouseholdAccountBookActivity.class);
            //年月日を受け渡す
            intent.putExtra("expenseYear",expenseYear);
            intent.putExtra("expenseMonth",expenseMonth);
            intent.putExtra("expenseDay",expenseDay);

            startActivity(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Button topButton = (Button)findViewById(R.id.topbutton);
        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispCalenderHouseholdAccountBookActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
