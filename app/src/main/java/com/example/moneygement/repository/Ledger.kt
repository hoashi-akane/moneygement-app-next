package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.LedgerQuery
import com.example.LedgersQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Ledger: GraphqlBase() {

    private val apolloClient = super.access()

    suspend fun getLedgerList(userId: Int): List<LedgersQuery.Ledger1>? {
        var ledgerIdList: List<LedgersQuery.Ledger1>? = null

        val job = GlobalScope.launch {
            var response = try{
                apolloClient.query(LedgersQuery(1)).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            var result = response.data?.ledger()
            if(result == null || response.hasErrors()){
                return@launch
            }else{
                ledgerIdList = result.ledgers()
            }
        }
        job.join()
        return ledgerIdList
    }

    suspend fun getLedger(id: Int): LedgerQuery.Ledger1?{
        var ledger: LedgerQuery.Ledger1? = null

        val job = GlobalScope.launch {
            var response = try{
                apolloClient.query(LedgerQuery(id)).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            val result = response.data?.ledger()
            if(result == null || response.hasErrors()){
                return@launch
            }else{
                ledger = result.ledger()
            }
        }
        job.join()
        return ledger
    }
}