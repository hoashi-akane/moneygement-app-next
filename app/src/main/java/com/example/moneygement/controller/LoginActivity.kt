package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R
import com.example.moneygement.repository.User
import kotlinx.coroutines.runBlocking

// import com.example.moneygement.DispCalendarIncomeHouseholdAccountBookActivity;
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        val loginbtn = findViewById<View>(R.id.loginbtn) as Button
        loginbtn.setOnClickListener {
            val et1 = findViewById<EditText>(R.id.id)
            val et2 = findViewById<EditText>(R.id.password)
            val email = et1.text.toString()
            val password = et2.text.toString()
            runBlocking {
                var loginInfo = User().login(email, password)
                if(loginInfo == null){
                    val intent = Intent(this@LoginActivity, LoginActivity::class.java)
                    startActivity(intent)

                }else{
                    val user= com.example.moneygement.model.User()
                    user.id = loginInfo.id()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}