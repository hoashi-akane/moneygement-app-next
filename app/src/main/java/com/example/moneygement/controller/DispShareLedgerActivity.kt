package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.LedgerQuery
import com.example.LedgersQuery
import com.example.ShareLedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerBinding
import com.example.moneygement.databinding.ActivityDispShareLedgerBinding
import com.example.moneygement.viewmodel.LedgerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DispShareLedgerActivity : AppCompatActivity() {
    private lateinit var incomeRecyclerView: RecyclerView
    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var incomeViewAdapter: RecyclerView.Adapter<*>
    private lateinit var expenseViewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var expenseViewManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityDispShareLedgerBinding
    private lateinit var shareLedgerList: List<ShareLedgersQuery.ShareLedger>
    private var incomeList= mutableListOf<LedgerQuery.Income>()
    private var expenseList= mutableListOf<LedgerQuery.Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_share_ledger)

        val viewModel : LedgerViewModel by viewModels()

        runBlocking{
            viewModel.getShareLedgerList(1)
            shareLedgerList = viewModel.shareItemList
        }
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_disp_share_ledger
        )
        binding.lifecycleOwner = this@DispShareLedgerActivity
        binding.vm = viewModel


        var ledgerListSpinner = findViewById<View>(R.id.list) as Spinner

        ledgerListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                GlobalScope.launch{
                    viewModel.listPositionToGetShareLedgerData(position)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        viewManager = LinearLayoutManager(this@DispShareLedgerActivity)
        expenseViewManager = LinearLayoutManager(this@DispShareLedgerActivity)
        incomeViewAdapter = IncomeRecyclerAdapter(incomeList)
        expenseViewAdapter = ExpenseRecyclerAdapter(expenseList)

        incomeRecyclerView = findViewById<RecyclerView>(R.id.income_rcycler).apply{

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = incomeViewAdapter
        }
        expenseRecyclerView = findViewById<RecyclerView>(R.id.expense_rcycler).apply{

            setHasFixedSize(true)
            layoutManager = expenseViewManager
            adapter = expenseViewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        var ledgerListSpinner = findViewById<View>(R.id.list) as Spinner
        var calendarView = findViewById<CalendarView>(R.id.calendarView)
        val viewModel = binding.vm
        if(viewModel == null){
            println("era- ")
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var strMonth = (month+1).toString()
            var strDate = dayOfMonth.toString()
            if (month+1 < 10) {
                strMonth = "0$strMonth"
            }
            if (dayOfMonth < 10) {
                strDate = "0$strDate"
            }

            incomeList = viewModel!!.ledger.incomes()
            expenseList = viewModel.ledger.expenses()
            val filterIncomeList = incomeList.filter {
                "$year-$strMonth-$strDate" == it.date().toString().substring(0, 10)
            }
            val filterExpenesList = expenseList.filter {
                "$year-$strMonth-$strDate" == it.date().toString().substring(0, 10)
            }
            incomeRecyclerView.adapter = IncomeRecyclerAdapter(filterIncomeList)
            expenseRecyclerView.adapter = ExpenseRecyclerAdapter(filterExpenesList)
            incomeViewAdapter.notifyDataSetChanged()
            expenseViewAdapter.notifyDataSetChanged()
        }


//      収入ボタン
        val incomeButton = findViewById<View>(R.id.inomeHouseHold) as Button
        incomeButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId

            val intent = Intent(this@DispShareLedgerActivity, DispShareIncomeCalendarActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", shareLedgerList[(index.toInt())].id())
            startActivity(intent)
        }

//      支出ボタン
        val expenseButton = findViewById<View>(R.id.expenseHouseHold) as Button
        expenseButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val intent = Intent(this@DispShareLedgerActivity, DispShareExpensesCalendarActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", shareLedgerList[(index.toInt())].id())
            startActivity(intent)
        }

//      チャットボタン
        val chatButton = findViewById<View>(R.id.imageButton10) as ImageButton
        chatButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val intent = Intent(this@DispShareLedgerActivity, ChatActivity::class.java)
            intent.putExtra("ledgerId", shareLedgerList[(index.toInt())].id())
            startActivity(intent)
        }


//      キャンセルボタン
        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispShareLedgerActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}