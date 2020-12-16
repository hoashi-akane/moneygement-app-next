package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispLedgerMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_ledger_menu)
    }

    override fun onResume() {
        super.onResume()

        val ledgerButton = findViewById<View>(R.id.ledgerbtn) as Button
        ledgerButton.setOnClickListener {
            val intent = Intent(this@DispLedgerMenuActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }
        val newledgerbtn = findViewById<View>(R.id.newledger) as Button
        newledgerbtn.setOnClickListener {
            val intent = Intent(this@DispLedgerMenuActivity, NewLedgerActivity::class.java)
            startActivity(intent)
        }
        val deleteledgerbtn = findViewById<View>(R.id.deleteledgerbtn) as Button
        deleteledgerbtn.setOnClickListener {
            val intent = Intent(this@DispLedgerMenuActivity, DeleteLedgerActivity::class.java)
            startActivity(intent)
        }
        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispLedgerMenuActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}