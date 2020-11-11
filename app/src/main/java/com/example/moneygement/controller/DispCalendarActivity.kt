package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_calendar)


        // CalendarViewにリスナーを設定
        (findViewById<View>(R.id.calendarview) as CalendarView).setOnDateChangeListener(listener)
    }

    /**
     * カレンダーの日付部分タップ時のリスナー
     */
    private var listener = OnDateChangeListener { _, year, month, dayOfMonth ->

        /**
         * 日付部分タップ時に実行される処理
         * @param view 押下されたカレンダーのインスタンス
         * @param year タップされた日付の「年」
         * @param month タップされた日付の「月」※月は0月から始まるから、+1して調整が必要
         * @param dayOfMonth タップされた日付の「日」
         */
        val insertMonth = month + 1
        val intent = Intent(this@DispCalendarActivity, InputSavingAmountActivity::class.java)
        val checkDay = year.toString() + '年' + insertMonth.toString() + '月' + dayOfMonth.toString() + '日'
        intent.putExtra("checkday", checkDay)
        startActivity(intent)


        // 引数の year, month, dayOfMonthを利用して、DBから予定表を取得
        // ListViewに予定表リストを突っ込む
    }

    override fun onResume() {
        super.onResume()
        val button = findViewById<View>(R.id.topbutton) as Button
        button.setOnClickListener {
            val intent = Intent(this@DispCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val expensesBtn = findViewById<View>(R.id.expenses) as Button
        expensesBtn.setOnClickListener{
            val intent = Intent(this@DispCalendarActivity, DispExpensesCalendarActivity::class.java)
            startActivity(intent)
        }

        val historyBtn = findViewById<View>(R.id.history) as Button
        historyBtn.setOnClickListener{
            val intent = Intent(this@DispCalendarActivity, DispSavingsHistoryActivity::class.java)
            startActivity(intent)
        }

        val cancelBtn = findViewById<View>(R.id.cancel) as Button
        cancelBtn.setOnClickListener{
            val intent = Intent(this@DispCalendarActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}