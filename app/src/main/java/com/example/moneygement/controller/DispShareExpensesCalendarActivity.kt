package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispShareExpensesCalendarActivity : AppCompatActivity() {
    private var ledgerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_calender_expenses_ledger)
        //CalenderViewにリスナーを設定
        (findViewById<View>(R.id.calendarview) as CalendarView).setOnDateChangeListener(listener)

        val oldIntent = intent
        ledgerId = oldIntent.getIntExtra("ledgerId",0)
        if(ledgerId == 0){
            var intent = Intent(this@DispShareExpensesCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //カレンダーの日付部分タップ時のリスナー
    var listener = OnDateChangeListener { view, year, month, dayOfMonth ->

        //view・・・押下されたカレンダーのインスタンス
        //year・・・タップされた日付の「年」
        //month・・・タップされた日付の「月」※月は０月から始まるから＋１する
        //dayOfMonth・・・タップされた日付の「日」
        val expenseMonth = month + 1

        //インテント生成
        val intent = Intent(this@DispShareExpensesCalendarActivity, InputShareExpensesActivity::class.java)
        //年月日を受け渡す
        intent.putExtra("ledgerId", ledgerId)
        val checkDay = year.toString() + '年' + expenseMonth.toString() + '月' + dayOfMonth.toString() + '日'
        intent.putExtra("checkDay", checkDay)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val topButton = findViewById<Button>(R.id.topbutton)
        topButton.setOnClickListener {
            val intent = Intent(this@DispShareExpensesCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val cancel = findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener {
            val intent = Intent(this@DispShareExpensesCalendarActivity, DispShareLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}