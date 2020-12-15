package com.example.moneygement.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.DeleteGroupMutation
import com.example.LedgersQuery
import com.example.ShareLedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDeleteGroupBindingImpl
import com.example.moneygement.databinding.ActivityInputLedgerChoiceBindingImpl
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import com.example.moneygement.viewmodel.LedgerChoiceViewModel
import kotlinx.coroutines.runBlocking

class DeleteGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteGroupBindingImpl
    private lateinit var ledgerList: List<ShareLedgersQuery.ShareLedger>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_group)

        val viewModel : LedgerChoiceViewModel by viewModels()

        var encryptedSharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        var userId = encryptedSharedPreferences.getInt("id", 0)

        runBlocking{
            viewModel.getShareLedgerList(userId)
            ledgerList = viewModel.shareLedgerList
        }
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_delete_group
        )
        binding.lifecycleOwner = this@DeleteGroupActivity
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()

        var ledgerListSpinner = findViewById<View>(R.id.groupList) as Spinner

        val choiceButton = findViewById<View>(R.id.button8) as Button
        choiceButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val deleteGroupMutation = DeleteGroupMutation.builder()
                    .id(ledgerList[index.toInt()].groupId())
                    .build()
            UserRepository().deleteGroup(deleteGroupMutation)
            val intent = Intent(this@DeleteGroupActivity, DispShareLedgerMenuActivity::class.java)
            startActivity(intent)
        }
    }
}