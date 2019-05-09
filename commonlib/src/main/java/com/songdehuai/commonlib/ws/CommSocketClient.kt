package com.songdehuai.commonlib.ws

import android.content.Context
import android.os.Handler
import com.songdehuai.commonlib.utils.LogUtil
import okhttp3.*
import java.util.concurrent.TimeUnit


object CommSocketClient {

    private lateinit var mWsManager: WsManager
    private val url = "ws://echo.websocket.org"
    
    fun connect(context: Context) {

        val mOkHttpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
            .build()

        mWsManager = WsManager.Builder(context).client(mOkHttpClient).wsUrl(url).build()
        mWsManager.wsStatusListener = object : WsStatusListener() {
            override fun onMessage(text: String?) {
                super.onMessage(text)
                LogUtil.i("接收:$text")
            }

            override fun onOpen(response: Response?) {
                super.onOpen(response)
                LogUtil.i("连接:${response.toString()}")
            }

            override fun onClosed(code: Int, reason: String?) {
                super.onClosed(code, reason)
                LogUtil.i("关闭:$reason")
            }

            override fun onFailure(t: Throwable?, response: Response?) {
                super.onFailure(t, response)
                t?.printStackTrace()
                LogUtil.i("错误:${response.toString()}")
            }

            override fun onReconnect() {
                super.onReconnect()
                LogUtil.i("重连")
            }
        }
        mWsManager.startConnect()
    }

    fun sendMsg(msg: String) {
        mWsManager.let {
            if (mWsManager.isWsConnected) {
                it.sendMessage(msg)
                LogUtil.i("发送$msg")
            }
        }
    }

    fun autoSend(text: String) {
        mSender = object : MessageSender {
            override fun message(): String {
                return text
            }
        }
        if (!isSend) {
            isSend = true
            sender()
        }
    }

    fun autoSend(sender: MessageSender) {
        mSender = sender
        if (!isSend) {
            isSend = true
            sender()
        }
    }

    fun cancelAutoSend() {
        isSend = false
    }

    fun sender() {
        senderHandler.postDelayed(reconnectRunnable, 5000)
    }

    private val reconnectRunnable = Runnable {
        mSender.let {
            sendMsg(it.message())
            if (isSend) {
                sender()
            }
        }
    }

    var isSend = false

    lateinit var mSender: MessageSender

    val senderHandler = Handler()

}
