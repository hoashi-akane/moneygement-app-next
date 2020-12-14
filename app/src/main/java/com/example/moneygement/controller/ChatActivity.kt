package com.example.moneygement.controller

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ChatFilterQuery
import com.example.moneygement.R
import com.example.moneygement.model.WEBSOCKET_URI
import com.example.moneygement.service.AuthService
import com.example.moneygement.service.ChatWebSocketClient
import com.example.NewChatMutation
import com.example.moneygement.repository.ChatRepository
import com.example.moneygement.service.ChatService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URI
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var messageList =  mutableListOf<Message>()

    private val uri = URI(WEBSOCKET_URI)
    private var client = ChatWebSocketClient(this, messageList, uri)

    private var message: Message = Message()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val rIntent = intent
        val ledgerId = rIntent.getIntExtra("ledgerId", 0)
        println(ledgerId)
        var sharedPreferences = AuthService().createAuthSharedPreferences(applicationContext)
        val userId = sharedPreferences.getInt("id", 0)
        val nickName = sharedPreferences.getString("nickName", "")

//      会員idもしくは家計簿id取得失敗
        if(userId == 0 || ledgerId == 0){

        }



//      部屋を移動するため部屋id、家計簿IDだけを入れて先に送信する
        message.roomId = ledgerId
        message.LedgerId = ledgerId
        message.UserId = userId
        message.nickName = nickName!!
        //      wsサーバと接続開始
        client.connectBlocking()

        if(client.isOpen) {
            val jsonObject = Gson().toJson(message)
            println("起動")
            //  何故か2回送信しないとサーバまで届かない．．．　注意
            client.send(jsonObject.toString())
            client.send(jsonObject.toString())
        }

//      値を取得
        var chatFilter = ChatFilterQuery.builder()
                .ledgerId(ledgerId)
                .first(1)
                .last(100)
                .build()
        runBlocking {
            messageList.addAll(ChatService().getMessageList(chatFilter))
//          WebSocket側が持っているlistにも反映
            client.messageList = messageList
        }

        //      recyclerViewの設定
        var linearLayoutManager = LinearLayoutManager(this@ChatActivity)
        linearLayoutManager.reverseLayout = true
        viewManager = linearLayoutManager
        viewAdapter = ChatRecyclerAdapter(messageList)
        recyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
//      更新
        updateData()
        viewAdapter.notifyDataSetChanged()
    }


    override fun onResume() {
        super.onResume()

        // 送信ボタンが押下された時
        sendMessageBtn.setOnClickListener {
            var item = findViewById<EditText>(R.id.sendMessage)

            message.Message = item.text.toString()

            val newChatMutation = NewChatMutation.builder()
                    .userId(message.UserId)
                    .ledgerId(message.LedgerId)
                    .comment(message.Message)
                    .build()

            ChatRepository().createChat(newChatMutation)

            val jsonObject = Gson().toJson(message)
            println(jsonObject.toString())
            if(client.isOpen) {
//              何故か2回送信しないとサーバまで届かない．．．　注意
                client.send(jsonObject.toString())
                client.send(jsonObject.toString())
            }
        }
    }

    class Message {
        var id = 0
        var LedgerId = 0
        var UserId = 0
        var Message = ""
        var roomId = 2
        var nickName = ""
        var postDate = ""
    }


    private fun updateData(){
        viewAdapter = ChatRecyclerAdapter(messageList)
        recyclerView.adapter = viewAdapter
    }

    fun setData(messageJsonObj: String){
        var jsonObj = Gson().toJson(messageJsonObj)
    }
}

