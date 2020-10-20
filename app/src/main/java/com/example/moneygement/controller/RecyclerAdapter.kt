package com.example.moneygement.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.SavingsDetailsQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.savings_history_list_item.view.*
import java.text.SimpleDateFormat

class RecyclerAdapter(private var savingsHistoryList: List<SavingsDetailsQuery.SavingsDetail>):
        RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.savings_history_list_item, parent, false) as View

        return MyViewHolder(view)
    }

    override fun getItemCount() = savingsHistoryList.size

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        var date  = savingsHistoryList[position].saving_date().toString().substring(0, 10).split("-")

        holder.view.savingDate.text = date[0]+"年"+date[1]+"月"+date[2]+"日"
        holder.view.savingAmount.text = "+" + savingsHistoryList[position].saving_amount().toString()
    }
}