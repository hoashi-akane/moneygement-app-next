package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Ledger: GraphqlBase() {

    private val apolloClient = super.access()

    suspend fun getLedgerList(userId: Int): List<LedgersQuery.Ledger1>? {
        var ledgerIdList: List<LedgersQuery.Ledger1>? = null

        val job = GlobalScope.launch {
            var response = try{
                apolloClient.query(LedgersQuery(userId)).await()
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

    suspend fun getAdviserLedgerList(adviserId: Int): List<AdviserLedgersQuery.AdviserLedger>? {
        var ledgerAdviserIdList: List<AdviserLedgersQuery.AdviserLedger>? = null

        val job = GlobalScope.launch {
            var response = try{
                apolloClient.query(AdviserLedgersQuery(adviserId)).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            var result = response.data?.ledger()
            if(result == null || response.hasErrors()){
                return@launch
            }else{
                ledgerAdviserIdList = result.adviserLedgers()
            }
        }
        job.join()
        return ledgerAdviserIdList
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

    fun insertExpense(createExpenseDetailMutation: CreateExpenseDetailMutation){

        GlobalScope.launch {

            val response = try{
                apolloClient.mutate(createExpenseDetailMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }
            if(response.hasErrors()){
                println("エラー")
            }
        }
    }

    fun insertIncome(createIncomeDetailMutation: CreateIncomeDetailMutation){

        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(createIncomeDetailMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.hasErrors()){
                println("エラー")
            }
        }
    }

    fun createLedger(createLedgerMutation: CreateLedgerMutation){
        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(createLedgerMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.hasErrors()){
                println("エラー")
            }
        }
    }

    fun deleteLedger(deleteLedgerMutation: DeleteLedgerMutation){
        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(deleteLedgerMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.hasErrors()){
                println("エラー")
            }
        }
    }

    fun addAdviser(addAdviserMutation: AddAdviserMutation){
        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(addAdviserMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.hasErrors()){
                println("エラー")
            }
        }
    }
}