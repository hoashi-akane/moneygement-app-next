package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.TargetAmountQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TargetAmount: GraphqlBase() {


    private val apolloClient = super.access()
    suspend fun getTargetAmount(targetAmountQuery: TargetAmountQuery): Int? {
        var result: TargetAmountQuery.SavingDetail? = null
        var targetAmount:Int?
            targetAmount = null

        val job = GlobalScope.launch {
            var response = try {
                apolloClient.query(targetAmountQuery).await()
            } catch (e: ApolloException) {
                e.printStackTrace()
                return@launch
            }
//            値を取る
            result = response.data?.saving()!!.savingDetail()

            if (result == null || response.hasErrors()) {
                return@launch
            } else {
                targetAmount = result!!.targetAmount()
                return@launch
            }
        }
        job.join()
        return targetAmount
    }
}
