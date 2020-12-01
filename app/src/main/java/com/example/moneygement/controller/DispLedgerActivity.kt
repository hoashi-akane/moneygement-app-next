package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.LedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerBinding
import com.example.moneygement.viewmodel.LedgerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.ParsePosition


//家計簿トップ
class DispLedgerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDispLedgerBinding
    private lateinit var ledgerList: List<LedgersQuery.Ledger1>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_ledger)

        val viewModel :LedgerViewModel by viewModels()

        runBlocking{
            viewModel.getLedgerList(1)
            ledgerList = viewModel.listItem
        }
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_disp_ledger
        )
        binding.lifecycleOwner = this@DispLedgerActivity
        binding.vm = viewModel


        var ledgerListSpinner = findViewById<View>(R.id.list) as Spinner

        ledgerListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                GlobalScope.launch{
                    viewModel.listPositionToGetLedgerData(position)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var ledgerListSpinner = findViewById<View>(R.id.list) as Spinner


        val incomeButton = findViewById<View>(R.id.inomeHouseHold) as Button
        incomeButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId

            val intent = Intent(this@DispLedgerActivity, DispCalendarIncomeLedgerActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", ledgerList[(index.toInt())].id())
            startActivity(intent)
        }
        val expenseButton = findViewById<View>(R.id.expenseHouseHold) as Button
        expenseButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val intent = Intent(this@DispLedgerActivity, DispCalenderExpensesLedgerActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", ledgerList[(index.toInt())].id())
            startActivity(intent)
        }
        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispLedgerActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}