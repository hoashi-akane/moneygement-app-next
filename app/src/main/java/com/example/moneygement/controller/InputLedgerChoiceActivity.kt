package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.LedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerBinding
import com.example.moneygement.databinding.ActivityInputLedgerChoiceBindingImpl
import com.example.moneygement.viewmodel.LedgerChoiceViewModel
import kotlinx.coroutines.runBlocking

class InputLedgerChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputLedgerChoiceBindingImpl
    private lateinit var ledgerList: List<LedgersQuery.Ledger1>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_ledger_choice)

        val viewModel : LedgerChoiceViewModel by viewModels()

        runBlocking{
            viewModel.getLedgerList(1)
            ledgerList = viewModel.listItem
        }
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_input_ledger_choice
        )
        binding.lifecycleOwner = this@InputLedgerChoiceActivity
        binding.vm = viewModel




        }

    override fun onRestart() {
        super.onRestart()

        var ledgerListSpinner = findViewById<View>(R.id.list) as Spinner
        val index = ledgerListSpinner.selectedItemId
        ledgerList[(index.toInt())].id()

        val comment = findViewById<View>(R.id.fname) as EditText


        val choiceButton = findViewById<View>(R.id.topbutton2) as Button
        choiceButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId

            val intent = Intent(this@InputLedgerChoiceActivity, DispLedgerActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", ledgerList[(index.toInt())].id())
            startActivity(intent)
        }
    }

}