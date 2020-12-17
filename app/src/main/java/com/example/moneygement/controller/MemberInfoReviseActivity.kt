package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.LoginQuery
import com.example.TargetAmountQuery
import com.example.UpdateUserMutation
import com.example.moneygement.R
import com.example.moneygement.model.User
import com.example.moneygement.repository.TargetAmount
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import kotlinx.android.synthetic.main.activity_member_info_revise.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MemberInfoReviseActivity : AppCompatActivity() {
    private var userInfo: LoginQuery.Login? = null
    private var targetAmountNumber = 0
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_info_revise)
    }

    override fun onResume() {
        super.onResume()

        var targetAmount = targetAmount
        targetAmount.isFocusable = false
        val name = txtNameView28
        name.isFocusable = false
        val nickName = txtNameView20
        nickName.isFocusable = false
        val mail = emailText
        mail.isFocusable = false
        val advisernickName = txtNameView26
        advisernickName.isFocusable = false
        val introduction = introductionEditText
        introduction.isFocusable = false

        val insertBtn = mypagebtn
        insertBtn.isEnabled = false
        val editBtn = editButton

        var encryptedSharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        var userId = encryptedSharedPreferences.getInt("id", 0)
        var email = encryptedSharedPreferences.getString("email", "")
        var password = encryptedSharedPreferences.getString("password", "")

        if(userId == 0 || email == "" || password == ""){
            AuthService().logout(applicationContext)
            var intent = Intent(this@MemberInfoReviseActivity, LoginActivity::class.java)
            startActivity(intent)
        }else{
            runBlocking{
                var result = AuthService().checkUserInfo(applicationContext)
                userInfo = result
                var targetAmountQuery = TargetAmountQuery.builder()
                        .userId(userId)
                        .build()
                targetAmountNumber = TargetAmount().getTargetAmount(targetAmountQuery) ?: 0
            }
        }

        if(userInfo != null){
            name.setText(userInfo!!.name(), TextView.BufferType.NORMAL)
            nickName.setText(userInfo!!.nickname(), TextView.BufferType.NORMAL)
            mail.setText(userInfo!!.email(), TextView.BufferType.NORMAL)
            advisernickName.setText(userInfo!!.adviser_name() ?: "", TextView.BufferType.NORMAL)
            introduction.setText(userInfo!!.introduction() ?: "", TextView.BufferType.NORMAL)
            targetAmount.setText(targetAmountNumber.toString(), TextView.BufferType.NORMAL)
        }

        editBtn.setOnClickListener {
            insertBtn.isEnabled = true
            targetAmount.isFocusable = true
            targetAmount.isFocusableInTouchMode = true
            name.isFocusable = true
            name.isFocusableInTouchMode = true
            nickName.isFocusable = true
            nickName.isFocusableInTouchMode = true
            mail.isFocusable = true
            mail.isFocusableInTouchMode = true
            advisernickName.isFocusable = true
            advisernickName.isFocusableInTouchMode = true
            introduction.isFocusable = true
            introduction.isFocusableInTouchMode = true
        }

        insertBtn.setOnClickListener {
            var updateUserMutation = UpdateUserMutation.builder()
                    .id(userId)
                    .targetAmount(targetAmount.text.toString().toInt())
                    .name(name.text.toString())
                    .nickname(nickName.text.toString())
                    .email(mail.text.toString())
                    .adviserName(advisernickName.text.toString())
                    .introduction(introduction.text.toString())
                    .build()
            runBlocking {

                user = UserRepository().updateUser(updateUserMutation)

                if(user != null){
                    var sharedPreferences = applicationContext.getSharedPreferences("user_info",0)

                    sharedPreferences.edit().apply {
                        putInt("id", user.id)
                        putString("name", user.name)
                        putString("email", user.mail)
                        putString("nickName", user.nickName)
                        apply()
                    }
                }
            }

            var intent = Intent(this@MemberInfoReviseActivity, MemberInfoReviseActivity::class.java)
            startActivity(intent)
        }

        val logoutBtn = logout_button
        logoutBtn.setOnClickListener {
            AuthService().logout(applicationContext)
            val intent = Intent(this@MemberInfoReviseActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@MemberInfoReviseActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}