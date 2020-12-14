package com.example.moneygement.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.AdviserListFilterQuery
import com.example.moneygement.R
import kotlinx.android.synthetic.main.activity_disp_adviser_list_item.view.*

class AdviserRecyclerAdapter(private var adviserList: List<AdviserListFilterQuery.AdviserList>, private val itemClickListener: MyViewHolder.OnItemClickListener): RecyclerView.Adapter<AdviserRecyclerAdapter.MyViewHolder>() {

    private var mRecyclerView : RecyclerView? = null

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        interface OnItemClickListener {
            fun onItemClick(view: View, position: Int, adviserItem: AdviserListFilterQuery.AdviserList)
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
                .inflate(R.layout.activity_disp_adviser_list_item, parent, false) as View

        view.setOnClickListener { mView ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(mView), adviserList[it.getChildAdapterPosition(mView)])
            }
        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.adviserName.text = adviserList[position].adviser_name()?.take(11)
        holder.view.introduction.text = adviserList[position].introduction()?.take(24)
    }

    override fun getItemCount() = adviserList.size
}