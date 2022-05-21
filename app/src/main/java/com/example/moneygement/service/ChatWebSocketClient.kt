package com.example.moneygement.service

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import com.example.moneygement.R
import com.example.moneygement.controller.ChatActivity
import com.example.moneygement.controller.ChatRecyclerAdapter
import com.google.gson.Gson
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class ChatWebSocketClient(
    private val activity: Activity,
    var messageList: MutableList<ChatActivity.Message>,
    uri: URI
) : WebSocketClient(uri) {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private val handler = Handler(Looper.getMainLooper())

    private val recyclerView: RecyclerView by lazy {
        activity.findViewById(R.id.chatRecyclerView)
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        println("サーバに接続")
    }

    override fun onMessage(message: String?) {

        println("受取")
        val messageModel: ChatActivity.Message =
            Gson().fromJson(message, ChatActivity.Message::class.java)
        messageList.add(0, messageModel)
        viewAdapter = ChatRecyclerAdapter(messageList)
        handler.post {
            recyclerView.adapter = viewAdapter
            viewAdapter.notifyDataSetChanged()
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        activity.runOnUiThread {
            println("閉じました")
        }
    }

    override fun onError(ex: Exception?) {
        println("エラー")
        println(ex)
    }
}