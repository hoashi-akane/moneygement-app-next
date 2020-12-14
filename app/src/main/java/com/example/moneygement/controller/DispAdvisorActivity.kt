package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R

class DispAdvisorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_advisor)
    }

    override fun onResume() {
        super.onResume()

        var advisor1 = findViewById<Button>(R.id.advisor1)
        advisor1.setOnClickListener{
            var i= Intent(this@DispAdvisorActivity,NewAdviserActivity::class.java)
            startActivity(i);
        };

        var advisor2 = findViewById<Button>(R.id.adviser2)
        advisor2.setOnClickListener{
            var i= Intent(this@DispAdvisorActivity,DispUserListActivity::class.java)
            startActivity(i);
        };

        var advisor3 = findViewById<Button>(R.id.advisor3)
        advisor3.setOnClickListener{
            var i= Intent(this@DispAdvisorActivity,AdvisorIntroductionActivity::class.java)
            startActivity(i);
        };

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispAdvisorActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}