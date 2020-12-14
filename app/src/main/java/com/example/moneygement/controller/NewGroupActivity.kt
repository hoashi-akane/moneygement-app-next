package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.CreateExpenseDetailMutation
import com.example.InsertGroupMutation
import com.example.moneygement.R
import com.example.moneygement.controller.NewGroupActivity
import com.example.moneygement.model.User
import com.example.moneygement.repository.Ledger
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class NewGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_newgroup)
    }

    override fun onResume() {
        super.onResume()

        //保持されてる会員IDと名前をとってくる処理
        val sharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        val id = sharedPreferences.getInt("id", 0)

        val button = findViewById<View>(R.id.inputGroupbtn) as Button

        //作成ボタンが押されたときの処理
        button.setOnClickListener { //EditTextを探す
            val editGroupName = findViewById<View>(R.id.groupName) as EditText
            val  editladgerName= findViewById<View>(R.id.ladgername) as EditText
            //EditTextから文字を取得
            val groupName = editGroupName.text.toString()
            val ladgerName = editladgerName.text.toString()

            /*ここでグループテーブルにグループIDを、
                家計簿テーブルに会員ID・グループ名・家計簿名
                を追加する処理*/

            val insertGroup = InsertGroupMutation.builder()
                    .userId(id)
                    .groupName(groupName)
                    .ledgerName(ladgerName)
                    .build()

            UserRepository().createGroup(insertGroup)

            //インテント生成
           val intent = Intent(this@NewGroupActivity, DispShareLedgerMenuActivity::class.java)
           startActivity(intent)
        }
        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@NewGroupActivity, DispShareLedgerMenuActivity::class.java)
            startActivity(intent)
        }
    }
}