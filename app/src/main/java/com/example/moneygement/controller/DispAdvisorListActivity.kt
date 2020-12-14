package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.AdviserListFilterQuery
import com.example.moneygement.R
import com.example.moneygement.repository.UserRepository
import kotlinx.android.synthetic.main.activity_disp_adviser_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.stream.Collectors

class DispAdvisorListActivity: AppCompatActivity(), AdviserRecyclerAdapter.MyViewHolder.OnItemClickListener {
    private lateinit var adviserRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: AdviserRecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var adviserList = mutableListOf<AdviserListFilterQuery.AdviserList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_adviser_list)

        recyclerAdapter = AdviserRecyclerAdapter(adviserList, this@DispAdvisorListActivity)
        viewManager = LinearLayoutManager(this@DispAdvisorListActivity)

        adviserRecyclerView = findViewById<RecyclerView>(R.id.adviserRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        val cancelBtn = cancel
        cancel.setOnClickListener{
            val intent = Intent(this@DispAdvisorListActivity, MainActivity::class.java)
            startActivity(intent)
        }

//      検索ボタン
        val changeBtn = changebtn3
        changeBtn.setOnClickListener {
            val containsString = editTextTextPersonName3.text.toString()
            println(containsString)
            val regex = Regex(containsString)
            var adviserFilterList = adviserList
                    .filter{regex.containsMatchIn(it.adviser_name().toString()) || regex.containsMatchIn(it.introduction().toString())}

            adviserRecyclerView.adapter = AdviserRecyclerAdapter(adviserFilterList, this@DispAdvisorListActivity)
            recyclerAdapter.notifyDataSetChanged()
        }

        runBlocking{
            var adviserListFilterQuery = AdviserListFilterQuery.builder()
                    .first(0)
                    .last(50)
                    .build()
            adviserList = UserRepository().getAdviserList(adviserListFilterQuery)!!
        }
        adviserRecyclerView.adapter = AdviserRecyclerAdapter(adviserList, this@DispAdvisorListActivity)
        recyclerAdapter.notifyDataSetChanged()

    }

    override fun onItemClick(view: View, position: Int, adviserItem: AdviserListFilterQuery.AdviserList) {
            val intent = Intent(this@DispAdvisorListActivity, DispAdvisorDetailActivity::class.java)
            println(adviserItem)
            intent.putExtra("id", adviserItem.id())
            intent.putExtra("introduction", adviserItem.introduction())
            intent.putExtra("adviserName", adviserItem.adviser_name())
            startActivity(intent)
    }
}