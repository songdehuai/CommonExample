package com.songdehuai.commonlib.ws

import android.content.Context
import com.songdehuai.commonlib.utils.LogUtil
import com.songdehuai.commonlib.wsmanager.WsManager
import com.songdehuai.commonlib.wsmanager.listener.WsStatusListener
import okhttp3.*
import java.util.concurrent.TimeUnit


object CommSocketClient {

    private lateinit var mWsManager: WsManager
//    private val url = "ws://echo.websocket.org"
    private val url = "ws://192.168.2.103:8888/8731b9b18af9ac848203515123a5093cdf20252a40e38edc6917aa13fe1720adff910741c9e799d85c28215f5135d4bc"

    fun connect(context: Context) {
        val mOkHttpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
            .build()
        mWsManager = WsManager.Builder(context).client(mOkHttpClient).wsUrl(url).build()
        mWsManager.setWsStatusListener(object : WsStatusListener() {
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
                LogUtil.i("错误:${response.toString()}")
            }

            override fun onReconnect() {
                super.onReconnect()
                LogUtil.i("重连")
            }
        })
        mWsManager.startConnect()
    }

    fun sendMsg(msg: String) {
        mWsManager.let {
            if (mWsManager.isWsConnected) {
                it.sendMessage(msg)
            }
        }
    }


}
