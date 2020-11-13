package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.LoginQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class User: GraphqlBase(){

    private val apolloClient = super.access()

    suspend fun login(email: String, password: String): LoginQuery.Login? {
        var loginQuery: LoginQuery.Login? = null
        var job = GlobalScope.launch {
            var response = try {
                apolloClient.query(LoginQuery(email, password)).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            val result = response.data
            if(result == null || response.hasErrors()){
                return@launch
            }else{
                loginQuery = result.login()
            }
        }

        job.join()
        return loginQuery
    }

}