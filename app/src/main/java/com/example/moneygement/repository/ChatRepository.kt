package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.ChatFilterQuery
import com.example.NewChatMutation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChatRepository: GraphqlBase(){

    private val apolloClient = super.access()

//  チャット投稿
    fun createChat(newChatMutation: NewChatMutation){
        GlobalScope.launch {
            val response = try{
                apolloClient.mutate(newChatMutation).await()
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

//　チャット一覧取得
    suspend fun getChatList(chatFillterQuery: ChatFilterQuery): MutableList<ChatFilterQuery.ChatList> {
        var chatQuery: List<ChatFilterQuery.ChatList>?
        var chatList = mutableListOf<ChatFilterQuery.ChatList>()
        var job = GlobalScope.launch{
            val response = try{
                apolloClient.query(chatFillterQuery).await()
            } catch(e: ApolloException){
                e.printStackTrace()
                return@launch
            }

            chatQuery = response.data?.chatList()

            if(response.hasErrors() || chatQuery == null){
                return@launch
            }else{
                chatList = chatQuery as MutableList<ChatFilterQuery.ChatList>
            }
        }
        job.join()
        return chatList
    }
}