package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.NewAdviserMutation
import com.example.moneygement.R
import com.example.moneygement.controller.NewAdviserActivity
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import kotlinx.android.synthetic.main.activity_newgroup.*

class NewAdviserActivity : AppCompatActivity() {
    private  var id :Int = 0
    private  var name :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //保持されてる会員IDと名前をとってくる処理
        val authService = AuthService()
        setContentView(R.layout.activity_newadviser)
        val sharedPreferences = authService.createAuthSharedPreferences(applicationContext)

        id = sharedPreferences.getInt("id", 0)
        name = sharedPreferences.getString("name", "")!!
    }

    override fun onResume() {
        super.onResume()

//      idがなければログインし直させる
        if(id == 0){
            intent = Intent(this@NewAdviserActivity, MainActivity::class.java)
            startActivity(intent)
        }

        //名前がすでに登録されていたら入力フォームに表示する処理
        if(name!="") {
            val tvOutput = findViewById<View>(R.id.name) as TextView
            //入力フォームに表示
            tvOutput.text = name
        }

        //アドバイザ登録ボタンが押されたときの処理
        val button = findViewById<View>(R.id.inputAdviserbtn) as Button

        button.setOnClickListener { //EditTextを探す
            val editName = findViewById<View>(R.id.name) as EditText
            val editAdviserName = findViewById<View>(R.id.abviserNickName) as EditText
            val editSelfIntroduction = findViewById<View>(R.id.selfIntroduction) as EditText

            //EditTextの文字を取得する
            val name = editName.text.toString()
            val adviserName = editAdviserName.text.toString()
            val selfIntroduction = editSelfIntroduction.text.toString()
            if (name != "" && adviserName != "" && selfIntroduction != "") {
                //なんかここでデータベースに名前とアドバイザニックネームと自己紹介を入れる処理したい
                val newAdviserMutation = NewAdviserMutation.builder()
                        .id(id)
                        .name(name)
                        .adviserName(adviserName)
                        .introduction(selfIntroduction)
                        .build()
                UserRepository().createAdviser(newAdviserMutation)
            }


            val intent = Intent(this@NewAdviserActivity, DispAdvisorActivity::class.java)
            startActivity(intent)
        }

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@NewAdviserActivity, DispAdvisorActivity::class.java)
            startActivity(intent)
        }
    }
}