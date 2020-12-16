package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.AddGroupMutation
import com.example.moneygement.R
import com.example.moneygement.repository.UserRepository
import kotlinx.android.synthetic.main.activity_invitation_group.*

class InvitationGroupActivity: AppCompatActivity() {
    var groupId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitation_group)

        var intent = intent
        groupId = intent.getIntExtra("groupId", groupId)
        println(groupId)
    }

    override fun onResume() {
        super.onResume()

        var inviteButton = inviteButton
        var email = invitationEmail
        var nickName = invitationNickName

        inviteButton.setOnClickListener{
            if(groupId == 0){
                var intent = Intent(this@InvitationGroupActivity, MainActivity::class.java)
                startActivity(intent)
            }

            var addGroupMutation = AddGroupMutation.builder()
                    .groupId(groupId)
                    .email(email.text.toString())
                    .nickName(nickName.text.toString())
                    .build()

            UserRepository().addGroup(addGroupMutation)

            var intent = Intent(this@InvitationGroupActivity, DispShareLedgerMenuActivity::class.java)
            startActivity(intent)
        }

        var cancel = cancel
        cancel.setOnClickListener {
            var intent = Intent(this@InvitationGroupActivity, DispShareLedgerActivity::class.java)
            startActivity(intent)
        }
    }
}