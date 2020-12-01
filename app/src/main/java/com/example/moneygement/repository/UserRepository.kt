package com.example.moneygement.repository

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.InsertUserMutation
import com.example.LoginQuery
import com.example.moneygement.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository: GraphqlBase(){

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

    fun createUser(user: InsertUserMutation): User {
        var userInfo = User()
        GlobalScope.launch {
            apolloClient
                    .mutate(user)
                    .enqueue(object : ApolloCall.Callback<InsertUserMutation.Data>() {
                        override fun onFailure(e: ApolloException) {
                            e.printStackTrace()
                        }

                        override fun onResponse(response: Response<InsertUserMutation.Data>) {
                            var result = response.data
                            if (result?.createUser() == null || response.hasErrors()) {
                            } else {
                                userInfo.id = result.createUser()!!.id()
                                userInfo.name = result.createUser()!!.name()
                                userInfo.mail = result.createUser()!!.email()
                                userInfo.nickName = result.createUser()!!.nickname()
                            }
                        }
                    })
        }
        return userInfo
    }
}