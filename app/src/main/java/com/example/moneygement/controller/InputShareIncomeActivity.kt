package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.CreateIncomeDetailMutation
import com.example.LedgerQuery
import com.example.moneygement.R
import com.example.moneygement.controller.InputIncomeHouseholdAccountBookActivity
import com.example.moneygement.repository.Ledger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class InputShareIncomeActivity : AppCompatActivity() {
    private lateinit var incomeYear: String
    private lateinit var incomeMonth: String
    private lateinit var incomeDay: String
    private lateinit var incomeDate: String
    private lateinit var insertDate: String
    private var ledgerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_income_household_account_book)
        val dateTextView = findViewById<View>(R.id.date) as TextView
        //Intentのオブジェクトを生成する
        val rIntent = intent

        ledgerId = rIntent.getIntExtra("ledgerId", 0)
        //年月日を受け取る
        incomeDate = rIntent.getStringExtra("checkDay")
        println(incomeDate)
        dateTextView.text = incomeDate
        insertDate = incomeDate.replace("[年月]".toRegex(), "-")
        insertDate = insertDate.replace("日","")
    }

    override fun onResume() {
        super.onResume()
        //エディットテキストを探す
        val continueButton = findViewById<View>(R.id.tocontinue) as Button
        val saveBtn = findViewById<View>(R.id.savebutton) as Button
        val cancelBtn = findViewById<View>(R.id.cancel) as Button
        val amounToMoney = findViewById<View>(R.id.editTextNumberSigned) as EditText
        val textPersonName = findViewById<View>(R.id.editTextTextPersonName) as EditText
        val textCategory = findViewById<View>(R.id.spinnerCategory) as Spinner

        continueButton.setOnClickListener {
            //              値を受取り
            val money = (amounToMoney.text.toString()).toInt()
            val note = textPersonName.text.toString()
            val categoryId = textCategory.selectedItemPosition

//              保存処理
            val createIncomeDetailMutation = CreateIncomeDetailMutation.builder()
                    .ledgerId(ledgerId)
                    .categoryId(categoryId + 1)
                    .date(insertDate)
                    .amount(money)
                    .note(note)
                    .build()

            Ledger().insertIncome(createIncomeDetailMutation)
//                    .

            val intent = Intent(this@InputShareIncomeActivity, InputShareIncomeActivity::class.java)
            //              同じ日付をセット（続けて入力する場合日付が必要
            intent.putExtra("checkDay", incomeDate)
            intent.putExtra("ledgerId", ledgerId)
            startActivity(intent)
        }

        saveBtn.setOnClickListener {
            val money = (amounToMoney.text.toString()).toInt()
            val note = textPersonName.text.toString()
            val categoryId = textCategory.selectedItemPosition

//              保存処理
            val createIncomeDetailMutation = CreateIncomeDetailMutation.builder()
                    .ledgerId(ledgerId)
                    .categoryId(categoryId + 1)
                    .date(insertDate)
                    .amount(money)
                    .note(note)
                    .build()

            Ledger().insertIncome(createIncomeDetailMutation)

            val intent = Intent(this@InputShareIncomeActivity, DispShareLedgerActivity::class.java)
            startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            val intent = Intent(this@InputShareIncomeActivity, DispShareIncomeCalendarActivity::class.java)
            intent.putExtra("ledgerId", ledgerId)
            startActivity(intent)
        }
    }
}