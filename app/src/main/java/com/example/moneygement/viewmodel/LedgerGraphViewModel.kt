package com.example.moneygement.viewmodel

import androidx.lifecycle.ViewModel
import com.example.LedgerQuery
import com.example.moneygement.model.GraphColor
import com.example.moneygement.repository.Ledger
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class LedgerGraphViewModel : ViewModel() {

    private var categoryExpensesMoney = mutableMapOf<String, Int>()
    private var categoryIncomesMoney = mutableMapOf<String, Int>()
    lateinit var incomeGraph: PieDataSet
    lateinit var expenseGraph: PieDataSet

    /**
     * データ取得と合計金額を集計、データセット（Facaade的役割）
     */
    suspend fun getLedgeGraphData(id: Int) =
        Ledger().getLedger(id)?.let {
            totalExpensesCategory(it.expenses())
            totalIncomesCategory(it.incomes())
            incomeGraph = setPieEntries(categoryIncomesMoney)
            expenseGraph = setPieEntries(categoryExpensesMoney)
        }

    /**
     * 引数の値をpieDataSetへ格納　引数のMapは（カテゴリ名,合計金額）
     */
    private fun setPieEntries(totalAmount: Map<String, Int>): PieDataSet {
        val pieEntries: MutableList<PieEntry> = ArrayList()
        totalAmount.forEach { (k, v) ->
            if (v != 0) {
                pieEntries.add(PieEntry(v.toFloat(), k))
            }
        }

        return PieDataSet(pieEntries, "").also {
            it.setAutomaticallyDisableSliceSpacing(true)
            it.valueTextSize = 15F
            it.colors = GraphColor().ORIGINAL_COLORS
        }
    }

    /**
     * 収入の合計金額をカテゴリ別に集計
     */
    private fun totalIncomesCategory(incomes: MutableList<LedgerQuery.Income>) {
        incomes.forEach {
            val name = it.category().Name()
            if (categoryIncomesMoney[name] == null) {
                categoryIncomesMoney[name] = 0
            }
            val nowIncomeTotal = categoryIncomesMoney[name]
            categoryIncomesMoney[name] = it.amount() + nowIncomeTotal!!
        }
    }

    /**
     * 支出の合計金額をカテゴリ別に集計
     */
    private fun totalExpensesCategory(expenses: MutableList<LedgerQuery.Expense>) {
        expenses.forEach {
            val name = it.category().Name()
            if (categoryExpensesMoney[name] == null) {
                categoryExpensesMoney[name] = 0
            }
            val nowExpensesTotal = categoryExpensesMoney[name]
            categoryExpensesMoney[name] = it.amount() + nowExpensesTotal!!
        }
        return
    }
}