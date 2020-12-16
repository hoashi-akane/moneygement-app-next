package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.SavingsDetailsQuery
import com.example.moneygement.R
import com.example.moneygement.repository.SavingsDetails
import kotlinx.android.synthetic.main.activity_disp_savingshistory.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DispSavingsHistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var savingsHistoryList = listOf<SavingsDetailsQuery.SavingsDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_savingshistory)


        viewManager = LinearLayoutManager(this@DispSavingsHistoryActivity)
        viewAdapter = RecyclerAdapter(savingsHistoryList)

        recyclerView = findViewById<RecyclerView>(R.id.savingsHistoryList).apply {
            //          修正する予定があるならtrue
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        MainScope().launch {
            var savingsDetailsQuery = SavingsDetailsQuery.builder()
                .savings_id(1)
                .first(1)
                .last(100)
                .build()

            savingsHistoryList = SavingsDetails().getSavingsHistory(savingsDetailsQuery)
            updateData()
            viewAdapter.notifyDataSetChanged()
            }

    }

    override fun onResume() {
        super.onResume()

        val cancelBtn = findViewById<View>(R.id.cancel)
        cancelBtn.setOnClickListener{
            val intent = Intent(this@DispSavingsHistoryActivity, DispCalendarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateData(){
        viewAdapter = RecyclerAdapter(savingsHistoryList)
        recyclerView.adapter = viewAdapter
    }
}