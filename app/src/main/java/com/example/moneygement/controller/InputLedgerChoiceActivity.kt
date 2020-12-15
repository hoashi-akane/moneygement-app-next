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
import com.example.AddAdviserMutation
import com.example.LedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerBinding
import com.example.moneygement.databinding.ActivityInputLedgerChoiceBindingImpl
import com.example.moneygement.repository.Ledger
import com.example.moneygement.service.AuthService
import com.example.moneygement.viewmodel.LedgerChoiceViewModel
import kotlinx.coroutines.runBlocking

class InputLedgerChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputLedgerChoiceBindingImpl
    private lateinit var ledgerList: List<LedgersQuery.Ledger1>
    private var adviserId = 0
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_ledger_choice)

        var intent = intent
        adviserId = intent.getIntExtra("adviserId",0)
        val viewModel : LedgerChoiceViewModel by viewModels()

        var encryptedSharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        userId = encryptedSharedPreferences.getInt("id", 0)


        runBlocking{
            viewModel.getLedgerList(userId)
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

        val comment = findViewById<View>(R.id.fname) as EditText


        val choiceButton = findViewById<View>(R.id.topbutton2) as Button
        choiceButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            if(adviserId != 0 && userId != 0){
                var addAdviserMutation = AddAdviserMutation.builder()
                        .adviserId(adviserId)
                        .ledgerId(ledgerList[index.toInt()].id())
                        .userId(userId)
                        .build()
                Ledger().addAdviser(addAdviserMutation)
            }

        val intent = Intent(this@InputLedgerChoiceActivity, DispLedgerActivity::class.java)
        startActivity(intent)
        }
    }

}