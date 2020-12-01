package com.example.moneygement.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.moneygement.R
import com.example.moneygement.controller.MainActivity
import com.example.moneygement.controller.DispLedgerActivity

class DispAdvisorDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_advisor_detail)
        val advisoricon = findViewById<ImageView>(R.id.imageView3)
        val selfintroduction = findViewById<View>(R.id.textView7) as TextView
        val advisorurl = findViewById<View>(R.id.textView13) as TextView
    }

    override fun onResume() {
        super.onResume()
        val back = findViewById<View>(R.id.cancel) as Button
        back.setOnClickListener {
            val intent = Intent(this@DispAdvisorDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val regist = findViewById<View>(R.id.button2) as Button
        regist.setOnClickListener {
            val intent = Intent(this@DispAdvisorDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val release = findViewById<View>(R.id.button7) as Button
        release.setOnClickListener {
            val intent = Intent(this@DispAdvisorDetailActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }
        val intent = Intent(this@DispAdvisorDetailActivity, MainActivity::class.java)
    }
}