package com.example.moneygement.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.UseAdvisermemberFilterQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.activity_disp_user_list_item.view.*

class UserListRecyclerAdapter(private var userList: List<UseAdvisermemberFilterQuery.UseAdviserMemberList>, private val itemClickListener: MyViewHolder.OnItemClickListener): RecyclerView.Adapter<UserListRecyclerAdapter.MyViewHolder>() {

    private var mRecyclerView: RecyclerView? = null

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        interface OnItemClickListener {
            fun onItemClick(view: View, position: Int, userItem: UseAdvisermemberFilterQuery.UseAdviserMemberList)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_disp_user_list_item, parent, false) as View

        view.setOnClickListener { mView ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(mView), userList[it.getChildAdapterPosition(mView)])
            }
        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.ledgerName.text = userList[position].ledgerName()
        holder.view.username.text = userList[position].nickName()
    }

    override fun getItemCount() = userList.size
}