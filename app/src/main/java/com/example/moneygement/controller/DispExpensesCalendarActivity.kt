package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispExpensesCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_expenses_calendar)

        (findViewById<View>(R.id.calendarview) as CalendarView).setOnDateChangeListener(listener)

    }

    private var listener = CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->

        /**
         * 日付部分タップ時に実行される処理
         * @param view 押下されたカレンダーのインスタンス
         * @param year タップされた日付の「年」
         * @param month タップされた日付の「月」※月は0月から始まるから、+1して調整が必要
         * @param dayOfMonth タップされた日付の「日」
         */

        var insertMonth = month+1
        val intent = Intent(this@DispExpensesCalendarActivity, InputSpendingExpenseAmountActivity::class.java)
        val checkDay = year.toString() + '年' + insertMonth.toString() + '月' + dayOfMonth.toString() + '日'
        intent.putExtra("checkday", checkDay)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val cancelBtn = findViewById<View>(R.id.cancel) as Button
        cancelBtn.setOnClickListener{
            val intent = Intent(this@DispExpensesCalendarActivity, DispCalendarActivity::class.java)
            startActivity(intent)
        }

        val topBtn = findViewById<View>(R.id.savebutton) as Button
        topBtn.setOnClickListener{
            val intent = Intent(this@DispExpensesCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}