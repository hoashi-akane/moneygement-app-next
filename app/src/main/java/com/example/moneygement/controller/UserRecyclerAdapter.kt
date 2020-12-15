package com.example.moneygement.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.InsertUserMutation
import com.example.SavingsDetailsQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.activity_disp_user_list_item.view.*
import kotlinx.android.synthetic.main.savings_history_list_item.view.*

class UserRecyclerAdapter {private var userList: List<InsertUserMutation.CreateUser>):
       RecyclerView.Adapter<UserRecyclerAdapter.UserMyViewHolder>()
    {

        class UserMyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_disp_user_list_item, parent, false) as View

            return UserMyViewHolder(view)
        }

        override fun getItemCount() = userList.size

        override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
            var user = userList[position].nickname().split("-")

            holder.view.username.text = user
        }
    }
}