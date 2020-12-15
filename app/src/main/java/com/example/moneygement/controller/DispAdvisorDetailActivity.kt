package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R
import kotlinx.android.synthetic.main.activity_disp_advisor_detail.*

class DispAdvisorDetailActivity : AppCompatActivity() {
    var id = 0
    var advisorNickname = ""
    var introduction = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_advisor_detail)

        val intent = intent
        id = intent.getIntExtra("id", 0)
        advisorNickname = intent.getStringExtra("adviserName")
        introduction = intent.getStringExtra("introduction")
    }

    override fun onResume() {
        super.onResume()
        val back = findViewById<View>(R.id.cancel) as Button
        back.setOnClickListener {
            val intent = Intent(this@DispAdvisorDetailActivity, DispAdvisorListActivity::class.java)
            startActivity(intent)
        }
        val regist = findViewById<View>(R.id.button2) as Button
        regist.setOnClickListener {
            val intent = Intent(this@DispAdvisorDetailActivity, InputLedgerChoiceActivity::class.java)
            intent.putExtra("adviserId", id)
            startActivity(intent)
        }
        val release = findViewById<View>(R.id.button7) as Button
        release.setOnClickListener {
            val intent = Intent(this@DispAdvisorDetailActivity, DispLedgerActivity::class.java)
            startActivity(intent)
        }


        val intent = Intent(this@DispAdvisorDetailActivity, MainActivity::class.java)
        var advisoricon = findViewById<ImageView>(R.id.imageView3)

        var selfintroduction = findViewById<View>(R.id.textView7) as TextView
        selfintroduction.text = introduction

        var advisorurl = findViewById<View>(R.id.textView13) as TextView
        advisorurl.text = ""

        val adviserName = adviserName
        adviserName.text = advisorNickname
    }
}