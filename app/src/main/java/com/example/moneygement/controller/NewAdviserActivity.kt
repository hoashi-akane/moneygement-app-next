package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R
import com.example.moneygement.controller.NewAdviserActivity
import com.example.moneygement.service.AuthService

class NewAdviserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //保持されてる会員IDと名前をとってくる処理
        val authService = AuthService()
        setContentView(R.layout.activity_newadviser)
        val sharedPreferences = authService.createAuthSharedPreferences(applicationContext)
        val id = sharedPreferences.getInt("id", 0)
        val name = sharedPreferences.getString("name", "")
        val intent = Intent(this@NewAdviserActivity, DispAdvisorActivity::class.java)
        intent.putExtra("userId", id)
        intent.putExtra("userName", name)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()


        //名前がすでに登録されていたら入力フォームに表示する処理
        val rIntent = intent
        val userId = rIntent.getIntExtra("userId", 0)
        val userName = rIntent.getStringExtra("userName")
        if (userName != "") {
            val tvOutput = findViewById<View>(R.id.name) as TextView
            //入力フォームに表示
            tvOutput.text = userName
        }

        //アドバイザ登録ボタンが押されたときの処理
        val button = findViewById<View>(R.id.inputAdviserbtn) as Button
        button.setOnClickListener { //EditTextを探す
            val editName = findViewById<View>(R.id.name) as EditText
            val editAdviserName = findViewById<View>(R.id.abviserNickName) as EditText
            val editSelfIntroduction = findViewById<View>(R.id.selfIntroduction) as EditText

            //インテント生成
            val intent = Intent(this@NewAdviserActivity, MainActivity::class.java)

            //EditTextの文字を取得する
            val name = editName.text.toString()
            val adviserName = editAdviserName.text.toString()
            val selfIntroduction = editSelfIntroduction.text.toString()
            if (name != "" && adviserName != "" && selfIntroduction != "") {
                //なんかここでデータベースに名前とアドバイザニックネームと自己紹介を入れる処理したい
            }
            startActivity(intent)
        }
        val cancelButton = findViewById<View>(R.id.cancel) as Button
        button.setOnClickListener {
            val intent = Intent(this@NewAdviserActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}