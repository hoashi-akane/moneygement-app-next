package com.example.moneygement.viewmodel

import android.view.View
import android.widget.AdapterView
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.LedgerQuery
import com.example.LedgersQuery
import com.example.ShareLedgersQuery
import com.example.moneygement.repository.Ledger
import com.example.moneygement.repository.ShareRepository


class LedgerViewModel : ViewModel() {

    var listItem = listOf<LedgersQuery.Ledger1>()
    var shareItemList = listOf<ShareLedgersQuery.ShareLedger>()
    var ledgerNameList = MutableLiveData<List<String>>()
    var amounts = MutableLiveData<Map<String, String>>(mapOf("totalIncomes" to "収入額　¥0", "totalExpenses" to "支出額 ¥0", "totalMoney" to "総資産額 ¥0"))
    var nameList= mutableListOf<String>()
    lateinit var ledger: LedgerQuery.Ledger1

    suspend fun getLedgerList(userId: Int){
        var result = Ledger().getLedgerList(userId)
        if(result != null){
            listItem = result
            result.forEach{
                nameList.add(it.name())
            }
        }
        ledgerNameList.postValue(nameList)
    }

    suspend fun getShareLedgerList(userId: Int){
        var result = ShareRepository().getShareLedgerList(userId)
        if(result != null){
            shareItemList = result
            result.forEach{
                nameList.add(it.name())
            }
        }
        ledgerNameList.postValue(nameList)
    }


    suspend fun getLedgerData(id: Int){
        var result = Ledger().getLedger(id)
        var amountList = mutableMapOf<String, String>()
        if(result != null) {
            ledger = result
            var totalIncomes = totalIncomesAmount(result.incomes())
            var totalExpenses = totalExpensesAmount(result.expenses())
            amountList["totalIncomes"] = "収入額 ¥$totalIncomes"
            amountList["totalExpenses"] = "支出額 ¥$totalExpenses"
            amountList["totalMoney"] = "総資産額 ¥"+ (totalIncomes - totalExpenses).toString()
            amounts.postValue(amountList)
        }
    }

    suspend fun listPositionToGetLedgerData(position: Int) {
        getLedgerData(listItem[position].id())
    }

    suspend fun listPositionToGetShareLedgerData(position: Int) {
        getLedgerData(shareItemList[position].id())
    }

    private fun totalIncomesAmount(incomes: MutableList<LedgerQuery.Income>): Int {
        var totalIncomeAmount = 0
        incomes.forEach{
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