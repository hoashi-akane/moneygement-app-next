package com.example.moneygement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.LedgerQuery
import com.example.moneygement.model.GraphColor
import com.example.moneygement.repository.Ledger
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.ArrayList

class LedgerGraphViewModel : ViewModel() {

    private var categoryExpensesMoney = mutableMapOf<String, Int>()
    private var categoryIncomesMoney = mutableMapOf<String, Int>()

    //カテゴリーIDとカテゴリー名
    var category = MutableLiveData<Map<Int, String>>(mapOf(1 to "生活費", 2 to "交通費",3 to "食費"));
    lateinit var incomeGraph: PieDataSet
    lateinit var expenseGraph: PieDataSet

//  データ取得と合計金額を集計、データセット（Facaade的役割）
    suspend fun getLedgeGraphData(id: Int) {

        var result = Ledger().getLedger(id)
        var graphlist = mutableMapOf<Int, String>()
        if (result != null) {
            totalExpensesCategory(result.expenses())
            totalIncomesCategory(result.incomes())
            incomeGraph = setPieEntries(categoryIncomesMoney)
            expenseGraph = setPieEntries(categoryExpensesMoney)
        }
        return
    }

//    引数の値をpieDataSetへ格納　引数のMapは（カテゴリ名,合計金額）
    fun setPieEntries(totalAmount: Map<String, Int>): PieDataSet {
        val pieEntries: MutableList<PieEntry> = ArrayList()
        totalAmount.forEach { (k, v) ->
            if(v != 0){
                pieEntries.add(PieEntry(v.toFloat(), k))
            }
        }
        var pieDataSet = PieDataSet(pieEntries, "")
        pieDataSet.setAutomaticallyDisableSliceSpacing(true)
    pieDataSet.colors = GraphColor().ORIGINAL_COLORS
        return pieDataSet
    }

    // 収入の合計金額をカテゴリ別に集計
    fun totalIncomesCategory(incomes: MutableList<LedgerQuery.Income>) {
        incomes.forEach{
            var name = it.category().Name()
            if(categoryIncomesMoney[name] == null){
                categoryIncomesMoney[name] = 0
           }
            var nowIncomeTotal = categoryIncomesMoney[name]
            categoryIncomesMoney[name] = it.amount() + nowIncomeTotal!!
        }
    }

//  支出の合計金額をカテゴリ別に集計s
    fun totalExpensesCategory(expenses: MutableList<LedgerQuery.Expense>){
        expenses.forEach{
            var name = it.category().Name()
            if(categoryExpensesMoney[name] == null){
                categoryExpensesMoney[name] = 0
            }
            var nowExpensesTotal = categoryExpensesMoney[name]
            categoryExpensesMoney[name] = it.amount() + nowExpensesTotal!!
        }
        return
    }
}