package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.CreateLedgerMutation
import com.example.moneygement.R
import com.example.moneygement.repository.Ledger
import com.example.moneygement.service.AuthService
import kotlinx.android.synthetic.main.activity_new_ledger.*

class NewLedgerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ledger)
    }

    override fun onResume() {
        super.onResume()

        var encryptedSharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        var userId = encryptedSharedPreferences.getInt("id", 0)

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@NewLedgerActivity, DispShareLedgerMenuActivity::class.java)
            startActivity(intent)
        }

        val createBtn = inputLedgerBtn
        createBtn.setOnClickListener{
            var ledgerName = ladgername.text.toString()
            var createLedgerMutation = CreateLedgerMutation.builder()
                    .userId(userId)
                    .name(ledgerName)
                    .build()

            Ledger().createLedger(createLedgerMutation)

            val intent = Intent(this@NewLedgerActivity, DispShareLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}