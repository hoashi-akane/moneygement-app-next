package com.example.moneygement.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.moneygement.R
import com.example.moneygement.databinding.ActivityDispLedgerGraphBinding
import com.example.moneygement.viewmodel.LedgerGraphViewModel
import com.example.moneygement.viewmodel.LedgerViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class DispLedgerGraphActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDispLedgerGraphBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp_ledger_graph)

//      viewModelを結び付けているが、binding的役割はない・・・泣
        val viewModel: LedgerGraphViewModel by viewModels()
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_disp_ledger_graph
        )
        binding.lifecycleOwner = this@DispLedgerGraphActivity
        binding.vm = viewModel

        var incomeGraph = findViewById<View>(R.id.chart1) as PieChart
        var expenseGraph = findViewById<View>(R.id.chart2) as PieChart

//      非同期でviewModelのメソッドを呼びデータが取得され次第、画面に適用させる。
        GlobalScope.launch {
            viewModel.getLedgeGraphData(1)
            incomeGraph.data = PieData(viewModel.incomeGraph)
            expenseGraph.data = PieData(viewModel.expenseGraph)
            incomeGraph.invalidate()
            expenseGraph.invalidate()
        }
    }


//    private fun setupPieChart() {
//        //PieEntriesのリストを作成する:
//        val pieEntries: MutableList<PieEntry> = ArrayList()
//        for (i in incomesTotalMoney.indices) {
//            pieEntries.add(PieEntry(incomesTotalMoney[i], category[i]))
//        }
//        //
//        val dataSet = PieDataSet(pieEntries, "Rainfall for Vancouver")
//        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
//        val data = PieData(dataSet)
//        //
//        //PieChartを取得する:
//        val piechart1 = findViewById<View>(R.id.chart1) as PieChart
//        piechart1.data = data
//        piechart1.invalidate()
//        val piechart2 = findViewById<View>(R.id.chart2) as PieChart
//        piechart2.data = data
//        piechart2.invalidate()
//        val cancel = findViewById<View>(R.id.cancel) as Button
//        cancel.setOnClickListener {
//            val intent = Intent(this@DispLedgerGraphActivity, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
}