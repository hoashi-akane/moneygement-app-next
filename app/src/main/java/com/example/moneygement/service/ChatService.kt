package com.example.moneygement.service

import com.example.ChatFilterQuery
import com.example.moneygement.controller.ChatActivity
import com.example.moneygement.repository.ChatRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChatService {

    private var oldDate = ""
    suspend fun getMessageList(chatFilter: ChatFilterQuery): MutableList<ChatActivity.Message> {
        var chatList = mutableListOf<ChatFilterQuery.ChatList>()
        var messageList = mutableListOf<ChatActivity.Message>()
        GlobalScope.launch{
            chatList = ChatRepository().getChatList(chatFilter)
        }.join()

        chatList = chatList.asReversed()

        chatList.forEach{
            var message = ChatActivity.Message()
            println(it.createdAt())
            message.nickName = it.nickname()
            message.Message = it.comment()
            message.postDate = getPostDate(it.createdAt() as String)



            messageList.add(message)
        }

        messageList = messageList.asReversed()
        return messageList
    }

    private fun getPostDate(createdAt: String): String{
        var postDate: String =""

        var date = createdAt.substring(0, createdAt.indexOf("T"))
        if(date != oldDate){
            oldDate = date
            var splitDate = date.split("-")
            postDate = String.format("%s年%s月%s日", splitDate[0], splitDate[1], splitDate[2])
        }

        var time = createdAt.substring(createdAt.indexOf("T")+1, createdAt.indexOf("Z")).split(":")
        postDate += (String.format("%s時%s分", time[0], time[1]))

        return postDate
    }
}