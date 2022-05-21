package com.example.moneygement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.AdviserLedgersQuery
import com.example.LedgerQuery
import com.example.LedgersQuery
import com.example.ShareLedgersQuery
import com.example.moneygement.repository.Ledger
import com.example.moneygement.repository.ShareRepository


class LedgerViewModel : ViewModel() {

    var listItem = listOf<LedgersQuery.Ledger1>()
    var shareItemList = listOf<ShareLedgersQuery.ShareLedger>()
    var adviserListItem = listOf<AdviserLedgersQuery.AdviserLedger>()
    var ledgerNameList = MutableLiveData<List<String>>()
    var amounts = MutableLiveData(
        mapOf(
            "totalIncomes" to "収入額　¥0",
            "totalExpenses" to "支出額 ¥0",
            "totalMoney" to "総資産額 ¥0"
        )
    )
    var nameList = mutableListOf<String>()
    lateinit var ledger: LedgerQuery.Ledger1

    suspend fun getLedgerList(userId: Int) {
        Ledger().getLedgerList(userId)?.let {
            // TODO:この1行消せそう
            listItem = it
            it.forEach { ledger ->
                nameList.add(ledger.name())
            }
            ledgerNameList.postValue(nameList)
        }
    }

    suspend fun getShareLedgerList(userId: Int) {
        ShareRepository().getShareLedgerList(userId)?.let {
            shareItemList = it
            it.forEach { shareLedger ->
                nameList.add(shareLedger.name())
            }
            ledgerNameList.postValue(nameList)
        }
    }

    suspend fun getAdviserLedgerList(adviserId: Int) {
        Ledger().getAdviserLedgerList(adviserId)?.let {
            adviserListItem = it
            it.forEach { adviserLedger ->
                nameList.add(adviserLedger.name())
            }
            ledgerNameList.postValue(nameList)
        }
    }


    private suspend fun getLedgerData(id: Int) {
        Ledger().getLedger(id)?.let {
            ledger = it
            val totalIncomes = totalIncomesAmount(it.incomes())
            val totalExpenses = totalExpensesAmount(it.expenses())
            amounts.postValue(
                mutableMapOf(
                    Pair("totalIncomes", "収入額 ¥$totalIncomes"),
                    Pair("totalExpenses", "支出額 ¥$totalExpenses"),
                    Pair("totalMoney", "総資産額 ¥${(totalIncomes - totalExpenses)}")
                )
            )
        }
    }

    suspend fun listPositionToGetLedgerData(position: Int) {
        getLedgerData(listItem[position].id())
    }

    suspend fun listPositionToGetShareLedgerData(position: Int) {
        getLedgerData(shareItemList[position].id())
    }

    suspend fun listPositionToGetAdviserLedgerData(position: Int) {
        getLedgerData(adviserListItem[position].id())
    }

    private fun totalIncomesAmount(incomes: MutableList<LedgerQuery.Income>): Int {
        var totalIncomeAmount = 0
        incomes.forEach {
            totalIncomeAmount += it.amount()
        }
        return totalIncomeAmount
    }

    private fun totalExpensesAmount(expenses: MutableList<LedgerQuery.Expense>): Int {
        var totalExpensesAmount = 0
        expenses.forEach {
            totalExpensesAmount += it.amount()
        }
        return totalExpensesAmount
    }

}