package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.LedgerQuery
import com.example.LedgersQuery
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerBinding
import com.example.moneygement.viewmodel.LedgerViewModel
import kotlinx.android.synthetic.main.activity_disp_ledger.*
import kotlinx.android.synthetic.main.activity_disp_share_ledger.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.ParsePosition


//家計簿トップ
class DispLedgerActivity : AppCompatActivity() {
    private lateinit var incomeRecyclerView: RecyclerView
    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var incomeViewAdapter: RecyclerView.Adapter<*>
    private lateinit var expenseViewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var expenseViewManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityDispLedgerBinding
    private lateinit var ledgerList: List<LedgersQuery.Ledger1>
    private var incomeList= mutableListOf<LedgerQuery.Income>()
    private var expenseList= mutableListOf<LedgerQuery.Expense>()
    private var ledgerId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_ledger)

        val intent = intent
        ledgerId = intent.getIntExtra("ledgerId", 0)

        val viewModel :LedgerViewModel by viewModels()

        runBlocking{
            viewModel.getLedgerList(1)
            ledgerList = viewModel.listItem
        }
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_disp_ledger
        )
        binding.lifecycleOwner = this@DispLedgerActivity
        binding.vm = viewModel


        var ledgerListSpinner = findViewById<View>(R.id.list) as Spinner

        ledgerListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                GlobalScope.launch{
                    viewModel.listPositionToGetLedgerData(position)
                    incomeList = viewModel!!.ledger.incomes()
                    expenseList = viewModel.ledger.expenses()
                    changeAdapter()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        viewManager = LinearLayoutManager(this@DispLedgerActivity)
        expenseViewManager = LinearLayoutManager(this@DispLedgerActivity)
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
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val viewModel = binding.vm
        if(viewModel == null){
            println("era- ")
        }

//        if(ledgerId != 0){
//            var count = 0
//            ledgerList.forEach{
//                if(it.id() == ledgerId){
//                    ledgerListSpinner.setSelection(count)
//                    return@forEach
//                }
//                count++
//            }
//        }

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


        val incomeButton = findViewById<View>(R.id.inomeHouseHold) as Button
        incomeButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId

            val intent = Intent(this@DispLedgerActivity, DispCalendarIncomeLedgerActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", ledgerList[(index.toInt())].id())
            startActivity(intent)
        }

        val expenseButton = findViewById<View>(R.id.expenseHouseHold) as Button
        expenseButton.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val intent = Intent(this@DispLedgerActivity, DispCalenderExpensesLedgerActivity::class.java)
//          家計簿IDを渡す
            intent.putExtra("ledgerId", ledgerList[(index.toInt())].id())
            startActivity(intent)
        }

        val cancelButton = findViewById<View>(R.id.cancel) as Button
        cancelButton.setOnClickListener {
            val intent = Intent(this@DispLedgerActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val graphBtn = graphBtn3
        graphBtn.setOnClickListener {
            val index = ledgerListSpinner.selectedItemId
            val intent = Intent(this@DispLedgerActivity, DispLedgerGraphActivity::class.java)
            intent.putExtra("ledgerId", ledgerList[index.toInt()].id())
            startActivity(intent)
        }
    }

    private fun changeAdapter(){
        val handler = Handler(Looper.getMainLooper())
        handler.post{
            incomeRecyclerView.adapter = IncomeRecyclerAdapter(incomeList)
            expenseRecyclerView.adapter = ExpenseRecyclerAdapter(expenseList)
            incomeViewAdapter.notifyDataSetChanged()
            expenseViewAdapter.notifyDataSetChanged()
        }
    }
}