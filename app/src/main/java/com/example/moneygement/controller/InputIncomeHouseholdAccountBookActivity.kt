package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R
import com.example.moneygement.controller.InputIncomeHouseholdAccountBookActivity

class InputIncomeHouseholdAccountBookActivity : AppCompatActivity() {
    private var incomeYear: String? = null
    private var incomeMonth: String? = null
    private var incomeDay: String? = null
    private var incomeDate: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_income_household_account_book)
        val date = findViewById<View>(R.id.date) as TextView
        //Intentのオブジェクトを生成する
        val rIntent = intent
        //年月日を受け取る
        incomeYear = rIntent.getIntExtra("incomeYear", 0).toString()
        incomeMonth = rIntent.getIntExtra("incomeMonth", 0).toString()
        incomeDay = rIntent.getIntExtra("incomeDay", 0).toString()
        incomeDate = incomeYear + "年" + incomeMonth + "月" + incomeDay + "日"
        date.text = incomeDate
    }

    override fun onResume() {
        super.onResume()
        //エディットテキストを探す
        val continueButton = findViewById<View>(R.id.tocontinue) as Button
        val saveBtn = findViewById<View>(R.id.savebutton) as Button
        val cancelBtn = findViewById<View>(R.id.cancel) as Button
        continueButton.setOnClickListener { //              値を受取り
            val amounToMoney = findViewById<View>(R.id.editTextNumberSigned) as EditText
            val textPersonName = findViewById<View>(R.id.editTextTextPersonName) as EditText
            val textCategory = findViewById<View>(R.id.spinnerCategory) as Spinner
            val txtmoney = amounToMoney.text.toString()
            val personName = textPersonName.text.toString()
            val category = textCategory.selectedItem.toString()

//              保存処理
            val intent = Intent(this@InputIncomeHouseholdAccountBookActivity, InputIncomeHouseholdAccountBookActivity::class.java)
            //              同じ日付をセット（続けて入力する場合日付が必要
            val money = "txtmoney".toInt()
            intent.putExtra("incomeYear", incomeYear)
            intent.putExtra("incomeMonth", incomeMonth)
            intent.putExtra("incomeDay", incomeDay)
            startActivity(intent)
        }
        saveBtn.setOnClickListener {
            val amounToMoney = findViewById<View>(R.id.editTextNumberSigned) as EditText
            val textPersonName = findViewById<View>(R.id.editTextTextPersonName) as EditText
            val textCategory = findViewById<View>(R.id.spinnerCategory) as Spinner
            val txtmoney = amounToMoney.text.toString()
            val personName = textPersonName.text.toString()
            val category = textCategory.selectedItem.toString()

//              保存処理
            val intent = Intent(this@InputIncomeHouseholdAccountBookActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            val intent = Intent(this@InputIncomeHouseholdAccountBookActivity, DispCalendarIncomeLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}