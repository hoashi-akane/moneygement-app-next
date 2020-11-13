package com.example.moneygement.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.view.View;
import android.widget.Button;

import com.example.moneygement.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DispLedgerGraphActivity extends AppCompatActivity {

    float rainfall[] = {98.8f, 123.8f, 34.6f, 43.9f, 69.4f};
    String monthNames[] = {"食費", "趣味", "光熱費", "交通費", "日用品"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_ledger_graph);

        setupPieChart();
    }

    private void setupPieChart() {
        //PieEntriesのリストを作成する:
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < rainfall.length; i++) {
            pieEntries.add(new PieEntry(rainfall[i], monthNames[i]));
        }
//
        PieDataSet dataSet = new PieDataSet(pieEntries, "Rainfall for Vancouver");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
//
        //PieChartを取得する:
        PieChart piechart1 = (PieChart) findViewById(R.id.chart1);
        piechart1.setData(data);
        piechart1.invalidate();

        PieChart piechart2 = (PieChart) findViewById(R.id.chart2);
        piechart2.setData(data);
        piechart2.invalidate();

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispLedgerGraphActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}