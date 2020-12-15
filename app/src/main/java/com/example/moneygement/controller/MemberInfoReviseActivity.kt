package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class MemberInfoReviseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_info_revise)
    }

    override fun onResume() {
        super.onResume()

        val targetAmount = findViewById<View>(R.id.editTextNumber) as TextView
        val name = findViewById<View>(R.id.txtNameView28) as TextView
        val nickName = findViewById<View>(R.id.txtNameView20) as TextView
        val mail = findViewById<View>(R.id.editTextNumberSigned) as TextView
        val advisernickName = findViewById<View>(R.id.txtNameView26) as TextView

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@MemberInfoReviseActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}