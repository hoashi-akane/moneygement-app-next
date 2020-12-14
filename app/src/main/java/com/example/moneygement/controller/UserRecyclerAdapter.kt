package com.example.moneygement.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.InsertUserMutation
import com.example.SavingsDetailsQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.savings_history_list_item.view.*

class UserRecyclerAdapter {private var userList: List<InsertUserMutation.CreateUser>):
       RecyclerView.Adapter<UserRecyclerAdapter.UserMyViewHolder>(){

        class UserMyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.savings_history_list_item, parent, false) as View

            return UserMyViewHolder(view)
        }

        override fun getItemCount() = userList.size

        override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
            var date  = userList[position].saving_date().toString().substring(0, 10).split("-")

            holder.view.savingDate.text = date[0]+"年"+date[1]+"月"+date[2]+"日"
            holder.view.savingAmount.text = "+" + userList[position].saving_amount().toString()
        }
}