package com.example.moneygement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.LedgersQuery
import com.example.moneygement.repository.Ledger
import kotlinx.coroutines.runBlocking

class LedgerViewModel : ViewModel() {

    private var listItem = listOf<LedgersQuery.Ledger1>()
    var ledgerNameList = MutableLiveData<List<String>>()
    var nameList= mutableListOf<String>()

    suspend fun getLedgerList(userId: Int){
        var result = Ledger().getLedgerList(1)
        if(result != null){
            listItem = result
            result.forEach{
                nameList.add(it.name())
            }
        }
        ledgerNameList.postValue(nameList)
    }

    suspend fun getLedgerData(id: Int){
        var result = Ledger().getLedger(id)
        if(result != null){

        }
    }
}