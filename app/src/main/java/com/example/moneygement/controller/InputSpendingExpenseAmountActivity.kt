package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R


class InputSpendingExpenseAmountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_spending_saving_amount)
    }

    override fun onResume() {
        super.onResume()
        val dateOutput = findViewById<View>(R.id.date) as TextView
        val continueBtn = findViewById<View>(R.id.tocontinue) as Button
        val cancelbtn = findViewById<View>(R.id.cancel) as Button
        val saveBtn = findViewById<View>(R.id.savebutton) as Button

//      日付の受取
        val rIntent = intent
        var date = rIntent.getStringExtra("checkday")
        dateOutput.text = date

        saveBtn.setOnClickListener{
//            graphql 受け取れる形に変形
            date = date.replace("[年月]".toRegex(), "-")
            date = date.replace("日", "")

            val savingAmount = findViewById<View>(R.id.money) as EditText
            val note = findViewById<View>(R.id.note) as EditText
            val money = savingAmount.text.toString().toInt()

            /**
             * ミューテーションに値をセット(Graphql未実装だから完成後)
             *
             */

        }

        cancelbtn.setOnClickListener{
            val intent = Intent(this@InputSpendingExpenseAmountActivity, DispExpensesCalendarActivity::class.java)
            startActivity(intent)
        }

        continueBtn.setOnClickListener{
            val intent = Intent(this@InputSpendingExpenseAmountActivity, DispExpensesCalendarActivity::class.java)
            startActivity(intent)
        }
    }
}