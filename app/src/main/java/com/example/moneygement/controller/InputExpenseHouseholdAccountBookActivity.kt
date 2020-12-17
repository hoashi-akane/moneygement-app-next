package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.CreateExpenseDetailMutation
import com.example.LedgerQuery
import com.example.LedgersQuery
import com.example.moneygement.R
import com.example.moneygement.controller.InputExpenseHouseholdAccountBookActivity
import com.example.moneygement.repository.Ledger
import kotlinx.android.synthetic.main.activity_input_share_income.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InputExpenseHouseholdAccountBookActivity : AppCompatActivity() {
    private lateinit var expenseYear: String
    private lateinit var expenseMonth: String
    private lateinit var expenseDay: String
    private lateinit var expenseDate: String
    private lateinit var insertDate: String
    private var ledgerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_household_account_book)
        val dateTextView = findViewById<View>(R.id.date) as TextView
        //Intentのオブジェクトを生成する
        val rIntent = intent

        ledgerId = rIntent.getIntExtra("ledgerId", 0)
        //年月日を受け取る
        expenseDate = rIntent.getStringExtra("checkDay")
        dateTextView.text = expenseDate
        insertDate = expenseDate.replace("[年月]".toRegex(), "-")
        insertDate = insertDate.replace("日","")
    }

    override fun onResume() {
        super.onResume()

        val continueButton = findViewById<View>(R.id.tocontinue) as Button
        val saveBtn = findViewById<View>(R.id.savebutton) as Button
        val cancelBtn = findViewById<View>(R.id.cancel) as Button
        val amounToMoney = findViewById<View>(R.id.editTextNumberSigned) as EditText
        val textPersonName = findViewById<View>(R.id.editTextTextPersonName) as EditText
        val textCategory = findViewById<View>(R.id.spinner) as Spinner

        continueButton.setOnClickListener { //エディットテキストを探す
            val money = amounToMoney.text.toString().toInt()
            val note = textPersonName.text.toString()
            val categoryId = textCategory.selectedItemPosition


//              保存処理
            val createExpenseDetailMutation = CreateExpenseDetailMutation.builder()
                    .ledgerId(ledgerId)
                    .categoryId(categoryId + 1)
                    .date(insertDate)
                    .amount(money)
                    .note(note)
                    .build()

            Ledger().insertExpense(createExpenseDetailMutation)

            val intent = Intent(this@InputExpenseHouseholdAccountBookActivity, InputExpenseHouseholdAccountBookActivity::class.java)
            intent.putExtra("checkDay", expenseDate)
            intent.putExtra("ledgerId", ledgerId)
            startActivity(intent)
        }
        saveBtn.setOnClickListener {
            val money = amounToMoney.text.toString().toInt()
            val note = textPersonName.text.toString()
            val categoryId = textCategory.selectedItemPosition


            //              保存処理
            val createExpenseDetailMutation = CreateExpenseDetailMutation.builder()
                    .ledgerId(ledgerId)
                    .categoryId(categoryId + 1)
                    .date(insertDate)
                    .amount(money)
                    .note(note)
                    .build()

            Ledger().insertExpense(createExpenseDetailMutation)

            val intent = Intent(this@InputExpenseHouseholdAccountBookActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            val intent = Intent(this@InputExpenseHouseholdAccountBookActivity, DispCalenderExpensesLedgerActivity::class.java)
            intent.putExtra("ledgerId", ledgerId)
            startActivity(intent)
        }
    }
}