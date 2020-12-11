package com.example.moneygement.service

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneygement.R
import com.example.moneygement.controller.ChatActivity
import com.example.moneygement.controller.ChatRecyclerAdapter
import com.example.moneygement.controller.RecyclerAdapter
import com.google.gson.Gson
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class ChatWebSocketClient(val activity: Activity, var messageList:MutableList<ChatActivity.Message>, uri: URI) : WebSocketClient(uri) {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    val handler = Handler(Looper.getMainLooper())

    private val recyclerView: RecyclerView by lazy {
        activity.findViewById<RecyclerView>(R.id.chatRecyclerView)
    }

    private val breakLine = System.lineSeparator()

//    接続開始
    override fun onOpen(handshakedata: ServerHandshake?) {
        println("サーバに接続")
    }

//    メッセージ受信
    override fun onMessage(message: String?) {

    println("受取")
    var messageModel: ChatActivity.Message = Gson().fromJson(message, ChatActivity.Message::class.java)
        messageList.add(0, messageModel)
        viewAdapter = ChatRecyclerAdapter(messageList)
        handler.post {
            recyclerView.adapter = viewAdapter
            viewAdapter.notifyDataSetChanged()
        }



    }

//    接続閉じ
    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        activity.runOnUiThread{
            println("閉じました")
        }
    }

//    エラー時
    override fun onError(ex: Exception?) {
        println("エラー")
        println(ex)
    }
}