package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.InsertSavingsDetailsMutation
import com.example.SavingAmountQuery
import com.example.SavingsDetailsQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SavingsDetails : GraphqlBase() {


    private val apolloClient = super.access()
    fun inputSavingsDetails(insertSavingsDetailsMutation: InsertSavingsDetailsMutation) {

        GlobalScope.launch {
            val response = try {
                apolloClient.mutate(insertSavingsDetailsMutation).await()
            } catch (e: ApolloException) {
                e.printStackTrace()
                return@launch
            }
//          取る値があれば値を取ってくる
            if (response.hasErrors()) {
                return@launch
            }
        }
    }

    suspend fun getSavingsHistory(savingsDetailsQuery: SavingsDetailsQuery): List<SavingsDetailsQuery.SavingsDetail> {
        var result: List<SavingsDetailsQuery.SavingsDetail>? = null
        var savingsHistoryList = mutableListOf<SavingsDetailsQuery.SavingsDetail>()

        val job = CoroutineScope(Dispatchers.IO).launch {
            var response = try {
                apolloClient.query(savingsDetailsQuery).await()
            } catch (e: ApolloException) {
                e.printStackTrace()
                return@launch
            }
//            値を取る
            result = response.data?.saving()?.savingsDetails()

            if (result == null || response.hasErrors()) {
                return@launch
            } else {
                savingsHistoryList = result as MutableList<SavingsDetailsQuery.SavingsDetail>
                return@launch
            }
        }
        job.join()
        return savingsHistoryList
    }

    suspend fun getSavingsAmount(savingAmountQuery: SavingAmountQuery): SavingAmountQuery.SavingAmount? {
        var result: SavingAmountQuery.Saving? = null
        var savingAmount: SavingAmountQuery.SavingAmount? = null
        var job = CoroutineScope(Dispatchers.IO).launch {
            var response = try {
                apolloClient.query(savingAmountQuery).await()
            } catch (e: ApolloException) {
                e.printStackTrace()
                return@launch
            }
            result = response.data?.saving()
            if (result == null || response.hasErrors()) {
            } else {
                savingAmount = result!!.savingAmount()
            }
        }
        job.join()
        return savingAmount
    }
}