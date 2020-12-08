package com.example.moneygement.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.moneygement.R
import com.example.moneygement.model.User
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.runBlocking

// import com.example.moneygement.DispCalendarIncomeHouseholdAccountBookActivity;
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        newMemberbtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, NewMemberActivity::class.java)
            startActivity(intent)
        }

        checkUser()
        val loginbtn = findViewById<View>(R.id.loginbtn) as Button

        loginbtn.setOnClickListener {
            val et1 = findViewById<EditText>(R.id.id)
            val et2 = findViewById<EditText>(R.id.password)
            val email = et1.text.toString()
            val password = et2.text.toString()

            var loginInfo = runBlocking {
                UserRepository().login(email, password)
            }

            if (loginInfo == null) {
                intent = Intent(this@LoginActivity, LoginActivity::class.java)
            } else {

                val user = AuthService().getLoginQueryToUserModel(loginInfo)
//              値を記憶させておく
                val sharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
                sharedPreferences.edit().apply {
                    putInt("id", user.id)
                    putString("name", user.name)
                    putString("email", user.mail)
                    putString("nickName", user.nickName)
                    putString("password", password)
                    commit()
                }

                intent = Intent(this@LoginActivity, MainActivity::class.java)
            }
            startActivity(intent)
        }
    }

    private fun checkUser(){
        val userInfo = AuthService().checkUserInfo(applicationContext)
        if(userInfo != null){
            intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}