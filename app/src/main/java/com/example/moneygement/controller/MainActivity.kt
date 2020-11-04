package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.DispBlogActivity
import com.example.moneygement.DispLedgerCalenderActivity
import com.example.moneygement.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume();

        var ibtn1 = findViewById<ImageButton>(R.id.imageButton5);
        ibtn1.setOnClickListener{
            var i = Intent(this@MainActivity, DispLedgerCalenderActivity::class.java);
            startActivity(i);
            };

        var ibtn2 = findViewById<ImageButton>(R.id.imageButton6);
        ibtn2.setOnClickListener{
                var i= Intent(this@MainActivity,InputSpendingExpenseAmountActivity::class.java);
                startActivity(i);
            };

        var ibtn3 = findViewById<ImageButton>(R.id.imageButton7);
            ibtn3.setOnClickListener{
                    var i= Intent(this@MainActivity,DispSavingsHistoryActivity::class.java);
                    startActivity(i);
                };

        var ibtn4 = findViewById<ImageButton>(R.id.imageButton8);
            ibtn4.setOnClickListener {
                    var i= Intent(this@MainActivity,InputSpendingExpenseAmountActivity::class.java);
                                startActivity(i);
                        };
        var ibtn5 = findViewById<ImageButton>(R.id.imageButton9);
                        ibtn5.setOnClickListener{
                    var i= Intent(this@MainActivity,DispBlogActivity::class.java);
                                startActivity(i);
                            };

//現在の貯金額を取得
//        val rIntent = intent
        //        Integer saving = rIntent.getIntExtra("");
//        dateOutput.setText();
//        //↑現在の貯金額が入ります
//
//
//        //目標貯金額を取得
//        Integer targetsaving = rIntent.getIntExtra("");
//        dateOutput.setTexr();
        //↑目標貯金額が入ります
    }
}