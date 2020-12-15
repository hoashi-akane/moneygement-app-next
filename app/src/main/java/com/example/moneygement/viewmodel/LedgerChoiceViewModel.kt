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
    var nameList= mutableListOf<String>()

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
            shareLedgerList = result
            result.forEach{
                nameList.add(it.name())
            }
        }
        ledgerNameList.postValue(nameList)
    }
}