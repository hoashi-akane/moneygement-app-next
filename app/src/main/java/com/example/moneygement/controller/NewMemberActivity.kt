package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.InsertUserMutation
import com.example.moneygement.R
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService

class NewMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newmember)
    }

    override fun onResume() {
        super.onResume()
        val inputBtn = findViewById<View>(R.id.MemberRegbtn) as Button
        inputBtn.setOnClickListener {
            //EditTextを探す
            val editName = findViewById<View>(R.id.Name) as EditText
            val editNickName = findViewById<View>(R.id.NickName) as EditText
            val editEmail = findViewById<View>(R.id.eMail) as EditText
            val editPass = findViewById<View>(R.id.password) as EditText
            val editPass2 = findViewById<View>(R.id.password2) as EditText
            //インテント生成
            val intent = Intent(this@NewMemberActivity, MainActivity::class.java)
            //文字取得
            val name = editName.text.toString()
            val nickName = editNickName.text.toString()
            val eMail = editEmail.text.toString()
            val pass = editPass.text.toString()
            val pass2 = editPass2.text.toString()
            if (name != "" && nickName != "" && eMail != "" && pass != "" && pass2 != "") {
                //空白の入力フォームがある場合の処理
            } else if (pass == pass2) {
                //パスワードと確認用パスワードが違う場合の処理

            } else {
                //サーバに会員情報を追加する処理

                //サーバに会員情報を追加する処理
                val insertUserMutation = InsertUserMutation.builder()
                        .name(name)
                        .nickName(nickName)
                        .email(eMail)
                        .password(pass)
                        .build()


                val userRepository = UserRepository()
                val user = userRepository.createUser(insertUserMutation)
                //値を記憶させておく
                val sharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
                sharedPreferences.edit().apply {
                    putInt("id", user.id)
                    putString("name", user.name)
                    putString("nickName", user.nickName)
                    putString("email", user.mail)
                    putString("password", pass)
                    commit()
                }
            }
        }
    }
}