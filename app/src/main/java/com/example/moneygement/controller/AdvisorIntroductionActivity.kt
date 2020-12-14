package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class AdvisorIntroductionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advisor_introduction)

    }

    override fun onResume() {
        super.onResume()

        var adviserBtn = findViewById<Button>(R.id.adviserBtn)
        adviserBtn.setOnClickListener{
            var i = Intent(this@AdvisorIntroductionActivity, DispAdvisorListActivity::class.java)
            startActivity(i);
        };

        var cancel = findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener{
            var i = Intent(this@AdvisorIntroductionActivity, MainActivity::class.java)
            startActivity(i);
        };
    }


}