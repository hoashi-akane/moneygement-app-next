package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.LedgersQuery
import com.example.moneygement.controller.DispCalendarIncomeHouseholdAccountBookActivity
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerBinding
import com.example.moneygement.repository.Ledger
import com.example.moneygement.viewmodel.LedgerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DispLedgerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDispLedgerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_ledger)

        val viewModel :LedgerViewModel by viewModels()

        binding = DataBindingUtil.setContentView<ActivityDispLedgerBinding>(
                this,
                R.layout.activity_disp_ledger
        )
        binding.lifecycleOwner = this@DispLedgerActivity
        binding.vm = viewModel

        var ledgerList = listOf<LedgersQuery.Ledger1>()
        var ledgerListView = findViewById<View>(R.id.list) as Spinner

        GlobalScope.launch{
            viewModel.getLedgerList(1)
        }

    }

    override fun onResume() {
        super.onResume()

        val incomeButton = findViewById<View>(R.id.inomeHouseHold) as Button
        incomeButton.setOnClickListener {
            val intent = Intent(this@DispLedgerActivity, DispCalendarIncomeHouseholdAccountBookActivity::class.java)
            startActivity(intent)
        }
        val expenseButton = findViewById<View>(R.id.expenseHouseHold) as Button
        expenseButton.setOnClickListener {
            val intent = Intent(this@DispLedgerActivity, DispCalenderLedgerActivity::class.java)
            startActivity(intent)
        }
        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispLedgerActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}