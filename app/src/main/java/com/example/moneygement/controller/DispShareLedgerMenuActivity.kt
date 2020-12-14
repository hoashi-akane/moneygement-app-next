package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispShareLedgerMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_share_ledger_menu)
    }

    override fun onResume() {
        super.onResume()


        var sharebtn = findViewById<Button>(R.id.sharebtn)
        sharebtn.setOnClickListener{
            var i= Intent(this@DispShareLedgerMenuActivity,DispShareLedgerActivity::class.java)
            startActivity(i);
        };

        var groupbtn = findViewById<Button>(R.id.groupbtn)
        groupbtn.setOnClickListener{
            var i= Intent(this@DispShareLedgerMenuActivity,NewGroupActivity::class.java)
            startActivity(i);
        };

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispShareLedgerMenuActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}