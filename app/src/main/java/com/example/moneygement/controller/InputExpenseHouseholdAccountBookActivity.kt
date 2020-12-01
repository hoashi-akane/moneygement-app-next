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
import com.example.moneygement.controller.InputExpenseHouseholdAccountBookActivity

class InputExpenseHouseholdAccountBookActivity : AppCompatActivity() {
    private var expenseYear: String? = null
    private var expenseMonth: String? = null
    private var expenseDay: String? = null
    private var expenseDate: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_household_account_book)
        val date = findViewById<View>(R.id.date) as TextView
        //Intentのオブジェクトを生成する
        val rIntent = intent
        //年月日を受け取る
        expenseYear = rIntent.getIntExtra("expenseYear", 0).toString()
        expenseMonth = rIntent.getIntExtra("expenseMonth", 0).toString()
        expenseDay = rIntent.getIntExtra("expenseDay", 0).toString()
        expenseDate = expenseYear + "年" + expenseMonth + "月" + expenseDay + "日"
        date.text = expenseDate
    }

    override fun onResume() {
        super.onResume()
        val continueButton = findViewById<View>(R.id.tocontinue) as Button
        val saveBtn = findViewById<View>(R.id.savebutton) as Button
        val cancelBtn = findViewById<View>(R.id.cancel) as Button
        continueButton.setOnClickListener { //エディットテキストを探す
            val Amountofmoney = findViewById<View>(R.id.editTextNumberSigned) as EditText
            val textPersonName = findViewById<View>(R.id.editTextTextPersonName) as EditText
            val textcategory = findViewById<View>(R.id.spinnerCategory) as Spinner
            val txtmoney = Amountofmoney.text.toString()
            val personName = textPersonName.text.toString()
            val category = textcategory.selectedItem.toString()

//              保存処理
            val intent = Intent(this@InputExpenseHouseholdAccountBookActivity, InputExpenseHouseholdAccountBookActivity::class.java)
            val money = "txtmoney".toInt()
            intent.putExtra("money", money)
            intent.putExtra("personName", personName)
            intent.putExtra("cotegory", category)
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
            val intent = Intent(this@InputExpenseHouseholdAccountBookActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            val intent = Intent(this@InputExpenseHouseholdAccountBookActivity, DispCalenderExpensesLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}