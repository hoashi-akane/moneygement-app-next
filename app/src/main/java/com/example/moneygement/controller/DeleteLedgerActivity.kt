package com.example.moneygement.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.DeleteGroupMutation
import com.example.DeleteLedgerMutation
import com.example.LedgersQuery
import com.example.ShareLedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDeleteGroupBindingImpl
import com.example.moneygement.databinding.ActivityDeleteLedgerBindingImpl
import com.example.moneygement.repository.Ledger
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import com.example.moneygement.viewmodel.LedgerChoiceViewModel
import kotlinx.coroutines.runBlocking

class DeleteLedgerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteLedgerBindingImpl
    private lateinit var ledgerList: List<LedgersQuery.Ledger1>
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_ledger)

        val viewModel : LedgerChoiceViewModel by viewModels()

        var encryptedSharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        var userId = encryptedSharedPreferences.getInt("id", 0)

        runBlocking{
            viewModel.getLedgerList(userId)
            ledgerList = viewModel.listItem
        }
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_delete_ledger
        )
        binding.lifecycleOwner = this@DeleteLedgerActivity
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()

        var ledgerListSpinner = findViewById<View>(R.id.ledgerList) as Spinner

        val choiceButton = findViewById<View>(R.id.button8) as Button
        choiceButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val deleteLedgerMutation = DeleteLedgerMutation.builder()
                    .userId(userId)
                    .ledgerId(ledgerList[index.toInt()].id())
                    .build()

            Ledger().deleteLedger(deleteLedgerMutation)
            val intent = Intent(this@DeleteLedgerActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}