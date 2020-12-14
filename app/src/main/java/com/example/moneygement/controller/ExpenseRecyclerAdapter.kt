package com.example.moneygement.controller

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.LedgerQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.disp_ledger_list_item.view.*

class ExpenseRecyclerAdapter(private var expenseList: List<LedgerQuery.Expense>) :RecyclerView.Adapter<ExpenseRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseRecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.disp_ledger_list_item, parent, false) as View

        return MyViewHolder(view)
    }

    override fun getItemCount() = expenseList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.ledger_list_amount.text = "支出: "+ expenseList[position].amount().toString()
        holder.view.ledger_list_amount.setBackgroundColor(Color.rgb(0,153,204))
        holder.view.chat_list_category.text = expenseList[position].category().Name()
    }
}