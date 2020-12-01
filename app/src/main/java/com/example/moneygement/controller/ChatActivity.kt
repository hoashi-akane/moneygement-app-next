package com.example.moneygement.controller

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneygement.R
import com.example.moneygement.model.WEBSOCKET_URI
import com.example.moneygement.service.ChatWebSocketClient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat.*
import java.net.URI

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var messageList =  mutableListOf<Message>()

    private val uri = URI(WEBSOCKET_URI)
    private val client = ChatWebSocketClient(this, messageList, uri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

//      wsサーバと接続開始
        client.connect()
//      ここでルームidを持ったmessageを生成


        viewManager = LinearLayoutManager(this@ChatActivity)
        viewAdapter = ChatRecyclerAdapter(messageList)
        recyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // messageListの値を変えたら
        updateData()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        // 送信ボタンが押下された時
        sendMessageBtn.setOnClickListener {
            var item = findViewById<EditText>(R.id.sendMessage)
            var message = Message()

            message.Message = item.text.toString()
            message.roomId = 2
            val jsonObject = Gson().toJson(message)
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
    }


    fun updateData(){
        viewAdapter = ChatRecyclerAdapter(messageList)
        recyclerView.adapter = viewAdapter
    }

    fun setData(messageJsonObj: String){
        var jsonObj = Gson().toJson(messageJsonObj)
    }
}

