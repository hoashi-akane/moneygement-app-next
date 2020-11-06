package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneygement.controller.MainActivity;

public class DispHouseholdAccountBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_household_account_book);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button incomeButton = (Button)findViewById(R.id.inomeHouseHold);
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispHouseholdAccountBookActivity.this,DispCalendarIncomeHouseholdAccountBookActivity.class);
                startActivity(intent);
            }
        });

        Button expenseButton = (Button)findViewById(R.id.expenseHouseHold);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispHouseholdAccountBookActivity.this,DispCalenderHouseholdAccountBookActivity.class);
                startActivity(intent);
            }
        });

        Button cancelButton = (Button)findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispHouseholdAccountBookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}