package com.example.moneygement.repository

//import com.example.InsertGroupMutation
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.*
import com.example.moneygement.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository: GraphqlBase(){

    private val apolloClient = super.access()

    suspend fun login(email: String, password: String): LoginQuery.Login? {
//      ログイン時に利用するFCMトークンを確認
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//        })


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

//    グループ作成
    fun createGroup(group: InsertGroupMutation){
        GlobalScope.launch {
            var response = try{
                apolloClient.mutate(group).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }
        }
    }

//   アドバイザ登録
    fun createAdviser(adivser: NewAdviserMutation){
        GlobalScope.launch {
            var response = try{
                apolloClient.mutate(adivser).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }
//            レスポンスエラー
            if(response.hasErrors()){
                return@launch
            }
        }
    }


//    アドバイザ一覧取得
    suspend fun getAdviserList(adviserListFilterQuery: AdviserListFilterQuery): MutableList<AdviserListFilterQuery.AdviserList>? {
        var result: MutableList<AdviserListFilterQuery.AdviserList>? = null

        val job = GlobalScope.launch {
            var response = try{
                apolloClient.query(adviserListFilterQuery).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.data?.adviserList() == null || response.hasErrors()){
                return@launch
            }else {
                result = response.data?.adviserList()!!
            }
        }
        job.join()

        return result
    }

//  グループ削除
    fun deleteGroup(deleteGroupMutation: DeleteGroupMutation){
        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(deleteGroupMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.hasErrors()){
                return@launch
            }
        }
    }

  // アドバイザ側ユーザ一覧
    suspend fun getUserList(useAdvisermemberFilterQuery: UseAdvisermemberFilterQuery): MutableList<UseAdvisermemberFilterQuery.UseAdviserMemberList>? {
        var result: MutableList<UseAdvisermemberFilterQuery.UseAdviserMemberList>? = null

        var job = GlobalScope.launch {
            var response = try{
                apolloClient.query(useAdvisermemberFilterQuery).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }
          
            if(response.data?.useAdviserMemberList() == null || response.hasErrors()){
                return@launch
            }else{
                result = response.data?.useAdviserMemberList()!!
            }
        }
        job.join()

        return result
    }

//   ユーザ情報更新
    suspend fun updateUser(updateUserMutation: UpdateUserMutation): User {
        var user = User()

        GlobalScope.launch {
            apolloClient
                    .mutate(updateUserMutation)
                    .enqueue(object : ApolloCall.Callback<UpdateUserMutation.Data>() {
                        override fun onFailure(e: ApolloException) {
                            e.printStackTrace()
                        }

                        override fun onResponse(response: Response<UpdateUserMutation.Data>) {
                            var result = response.data
                            if (result?.updateUser() == null || response.hasErrors()) {
                            } else {
                                user.id = result.updateUser()!!.id()
                                user.name = result.updateUser()!!.name()
                                user.mail = result.updateUser()!!.email()
                                user.nickName = result.updateUser()!!.nickname()
                            }
                        }
                    })
        }.join()
        return user
    }      
      //  グループ招待
    fun addGroup(addGroupMutation: AddGroupMutation){
        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(addGroupMutation).await()
            }catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            if(response.hasErrors()){
                return@launch
            }
        }
    }
}