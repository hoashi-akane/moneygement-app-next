package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moneygement.R
import com.example.moneygement.service.AuthService

class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logoutconfirm)
    }

    override fun onResume() {
        super.onResume()
        AuthService().logout(applicationContext)
        intent = Intent(this@LogoutActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}