package com.example.moneygement.controller

import android.graphics.Color
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.LedgerQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.disp_ledger_list_item.view.*

class IncomeRecyclerAdapter(private var incomeList: List<LedgerQuery.Income>) :RecyclerView.Adapter<IncomeRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeRecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.disp_ledger_list_item, parent, false) as View

        return MyViewHolder(view)
    }

    override fun getItemCount() = incomeList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.ledger_list_amount.text = "収入: "+ incomeList[position].amount().toString()
        holder.view.ledger_list_amount.setBackgroundColor(Color.rgb(3,218,197))
        holder.view.chat_list_category.text = incomeList[position].category().Name()
    }
}