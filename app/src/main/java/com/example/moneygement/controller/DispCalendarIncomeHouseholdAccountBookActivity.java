package com.example.moneygement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.moneygement.controller.MainActivity;

import java.util.Calendar;

public class DispCalendarIncomeHouseholdAccountBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_calendar_income_household_account_book);
        //CalenderViewにリスナーを設定
        ((CalendarView) findViewById(R.id.calendarview)).setOnDateChangeListener(listener);
    }




    CalendarView.OnDateChangeListener listener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            Integer incomeYear = year;
            Integer incomeMonth = month+1;
            Integer incomeDay = dayOfMonth;

            //インテント生成
            Intent intent = new Intent
                    (DispCalendarIncomeHouseholdAccountBookActivity.this,InputIncomeHouseholdAccountBookActivity.class);
            //年月日を受け渡す
            intent.putExtra("incomeYear",incomeYear);
            intent.putExtra("incomeMonth",incomeMonth);
            intent.putExtra("incomeDay",incomeDay);

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
                Intent intent = new Intent(DispCalendarIncomeHouseholdAccountBookActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
