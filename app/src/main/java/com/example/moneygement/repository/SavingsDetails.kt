package com.example.moneygement.repository

import android.app.Service
import android.provider.Settings
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.InsertSavingsDetailsMutation
import com.example.SavingsDetailsQuery
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import kotlin.coroutines.CoroutineContext

class SavingsDetails: GraphqlBase() {


    private val apolloClient = super.access()
    fun inputSavingsDetails(insertSavingsDetailsMutation: InsertSavingsDetailsMutation){

        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(insertSavingsDetailsMutation).await()
            } catch (e: ApolloException){
                e.printStackTrace()
                return@launch
            }
//          取る値があれば値を取ってくる
            if(response.hasErrors()){
                return@launch
            }
        }
    }

    suspend fun getSavingsHistory(savingsDetailsQuery: SavingsDetailsQuery): List<SavingsDetailsQuery.SavingsDetail> {
        var result :List<SavingsDetailsQuery.SavingsDetail>? = null
        var savingsHistoryList = mutableListOf<SavingsDetailsQuery.SavingsDetail>()

        val job = GlobalScope.launch{
            var response = try{
                apolloClient.query(savingsDetailsQuery).await()
            }catch (e: ApolloException){
                e.printStackTrace()
                return@launch
            }
//            値を取る
            result = response.data?.saving()?.savingsDetails()

            if(result == null || response.hasErrors()){
                return@launch
            }else{
                savingsHistoryList = result as MutableList<SavingsDetailsQuery.SavingsDetail>
                return@launch
            }
        }
        job.join()
        return savingsHistoryList
    }
}