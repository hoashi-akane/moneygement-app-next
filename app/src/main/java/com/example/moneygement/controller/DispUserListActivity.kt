package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.UseAdvisermemberFilterQuery
import com.example.moneygement.R
import com.example.moneygement.repository.UserRepository
import com.example.moneygement.service.AuthService
import kotlinx.coroutines.runBlocking

class DispUserListActivity: AppCompatActivity(), UserListRecyclerAdapter.MyViewHolder.OnItemClickListener {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: UserListRecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var userList = mutableListOf<UseAdvisermemberFilterQuery.UseAdviserMemberList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_user_list)

        recyclerAdapter = UserListRecyclerAdapter(userList, this@DispUserListActivity)
        viewManager = LinearLayoutManager(this@DispUserListActivity)

        userRecyclerView = findViewById<RecyclerView>(R.id.userList).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        var encryptedSharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        var userId = encryptedSharedPreferences.getInt("id", 0)

        var useAdvisermemberFilterQuery = UseAdvisermemberFilterQuery.builder()
                .userId(userId)
                .first(0)
                .last(100)
                .build()

        runBlocking {
            userList = UserRepository().getUserList(useAdvisermemberFilterQuery)!!
        }
        userRecyclerView.adapter = UserListRecyclerAdapter(userList, this@DispUserListActivity)
        recyclerAdapter.notifyDataSetChanged()
    }

//  押された場合の処理
    override fun onItemClick(view: View, position: Int, userItem: UseAdvisermemberFilterQuery.UseAdviserMemberList) {
        val intent = Intent(this@DispUserListActivity, DispLedgerActivity::class.java)
        intent.putExtra("ledgerId", userList[position].id())
        startActivity(intent)
    }
}