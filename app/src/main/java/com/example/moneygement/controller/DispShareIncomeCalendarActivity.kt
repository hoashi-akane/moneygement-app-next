package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispShareIncomeCalendarActivity : AppCompatActivity() {
    private var ledgerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_calendar_income_ledger)
        //CalenderViewにリスナーを設定
        (findViewById<View>(R.id.calendarview) as CalendarView).setOnDateChangeListener(listener)

        val oldIntent = intent
        ledgerId = oldIntent.getIntExtra("ledgerId",0)
        if(ledgerId == 0){
            var intent = Intent(this@DispShareIncomeCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    var listener = OnDateChangeListener { view, year, month, dayOfMonth ->
        val incomeMonth = month + 1

        //インテント生成
        val intent = Intent(this@DispShareIncomeCalendarActivity, InputShareIncomeActivity::class.java)
        intent.putExtra("ledgerId", ledgerId)
        //年月日を受け渡す
        val checkDay = year.toString() + '年' + incomeMonth.toString() + '月' + dayOfMonth.toString() + '日'
        intent.putExtra("checkDay", checkDay)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val topButton = findViewById<View>(R.id.topbutton) as Button
        topButton.setOnClickListener {
            val intent = Intent(this@DispShareIncomeCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val cancel = findViewById<View>(R.id.cancel) as Button
        cancel.setOnClickListener {
            val intent = Intent(this@DispShareIncomeCalendarActivity, DispShareLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}