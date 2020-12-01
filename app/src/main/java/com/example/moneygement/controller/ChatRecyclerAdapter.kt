package com.example.moneygement.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.SavingsDetailsQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.activity_chat_list_item.view.*
import kotlinx.android.synthetic.main.savings_history_list_item.view.*
import java.text.SimpleDateFormat

class ChatRecyclerAdapter(private var messageList: List<ChatActivity.Message>):
        RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_chat_list_item, parent, false) as View

        return MyViewHolder(view)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.comment.text = messageList[position].Message
        holder.view.name.text = messageList[position].UserId.toString()
    }

}