package com.songdehuai.commonexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.WebSocket;
import com.songdehuai.commonlib.utils.AppUtils;
import com.songdehuai.commonlib.utils.FreeSync;

import java.util.UUID;

import androidx.annotation.Nullable;

public class SocketService extends Service {


    public static final String SOCKETFREESYNCNAME = "SOCKET";

    public static final String SOCKETFREESYNCNAME_MESSAGE = "SOCKET_MESAGE";

    public static final String SOCKETFREESYNCNAME_SEND = "SOCKET_MESAGE_SEND";


    FreeSync freeSync;

    WebSocket mWebSocket;

    AsyncHttpGet asyncHttpGet;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        freeSync = FreeSync.freeSyncWithName(SOCKETFREESYNCNAME);
        //启动连接Socket
        AsyncHttpClient.getDefaultInstance().websocket("ws://192.168.2.76:7397/0108b790ac5269bd5288a62ea574f593596dd2d531b7c8f81b3654430c9c36f7fc8b4dd0d6b1849a4690721afb264e45", null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception e, WebSocket webSocket) {
                e.printStackTrace();
                Log.i("Socket", e.getMessage());
                mWebSocket = webSocket;
                if (mWebSocket != null) {
                    Util.writeAll(mWebSocket, "心跳".getBytes(), new CompletedCallback() {
                        @Override
                        public void onCompleted(Exception e) {
                            Log.i("Socket心跳", "ss");
                        }
                    });
                    mWebSocket.setStringCallback(new WebSocket.StringCallback() {
                        @Override
                        public void onStringAvailable(String s) {
                            Log.i("Socket收到消息", s);
                            freeSync.callBack(SOCKETFREESYNCNAME_MESSAGE, s);
                        }
                    });
                }
            }
        });
        

        freeSync.addCallBack(SOCKETFREESYNCNAME_SEND, (name, object) -> {
            mWebSocket.send(object.toString());
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
