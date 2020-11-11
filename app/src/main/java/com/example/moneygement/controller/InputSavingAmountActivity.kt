package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.InsertSavingsDetailsMutation
import com.example.moneygement.R
import com.example.moneygement.repository.SavingsDetails

class InputSavingAmountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_saving_amount)
    }

    override fun onResume() {
        super.onResume()
        val dateOutput = findViewById<View>(R.id.date) as TextView
        val continueBtn = findViewById<View>(R.id.tocontinue) as Button
        val cancelbutton = findViewById<View>(R.id.cancel) as Button
        val saveButton = findViewById<Button>(R.id.savebutton)

        //日付の受け取り
        val rIntent = intent
        var date = rIntent.getStringExtra("checkday")
        dateOutput.text = date
        println(date)

        saveButton.setOnClickListener{
//          graphqlが受け取れる形に整形
            date = date.replace("[年月]".toRegex(), "-")
            date = date.replace("日","")


            val savingAmount = findViewById<EditText>(R.id.money) as EditText
            val note = findViewById<EditText>(R.id.note) as EditText
            val money = savingAmount.text.toString().toInt()

//          ミューテーションに値をセット
            val insertSavingsDetailsMutation = InsertSavingsDetailsMutation.builder()
                    .savingId(1)
                    .savingAmount(money)
                    .note(note.text.toString())
                    .savingDate(date)
                    .build()
            SavingsDetails().inputSavingsDetails(insertSavingsDetailsMutation)
        }

        //キャンセルボタン
        cancelbutton.setOnClickListener {
            val intent = Intent(this@InputSavingAmountActivity, DispCalendarActivity::class.java)
            startActivity(intent)
        }

        //続けて記入ボタン
        continueBtn.setOnClickListener {
            val intent = Intent(this@InputSavingAmountActivity, DispCalendarActivity::class.java)
            startActivity(intent)
        }
    }
}