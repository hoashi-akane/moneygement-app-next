package com.example.moneygement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.LedgersQuery
import com.example.ShareLedgersQuery
import com.example.moneygement.repository.Ledger
import com.example.moneygement.repository.ShareRepository

class LedgerChoiceViewModel : ViewModel() {
    var listItem = listOf<LedgersQuery.Ledger1>()
    var ledgerNameList = MutableLiveData<List<String>>()
    var shareLedgerList = listOf<ShareLedgersQuery.ShareLedger>()
    private var nameList = mutableListOf<String>()

    suspend fun getLedgerList(userId: Int) {
        Ledger().getLedgerList(userId)?.let { list ->
            listItem = list
            list.forEach {
                nameList.add(it.name())
            }
            ledgerNameList.postValue(nameList)
        }
    }

    suspend fun getShareLedgerList(userId: Int) {
        ShareRepository().getShareLedgerList(userId)?.let { list ->
            shareLedgerList = list
            list.forEach {
                nameList.add(it.name())
            }
            ledgerNameList.postValue(nameList)
        }
    }
}