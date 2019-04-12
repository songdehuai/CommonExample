package com.songdehuai.commonlib.utils

import android.util.ArrayMap
import okgo.callback.Callback

import java.util.ArrayList
import java.util.LinkedHashMap


/**
 * @author songdehuai
 */
class FreeSyncTest private constructor() {

    private val freeSyncCallbackHashMap = LinkedHashMap<String, ArrayList<FreeSyncCallback>>()

    fun addCallBack(name: String, freeSyncCallback: FreeSyncCallback) {
        var isHas = false
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                freeSyncCallbackHashMap[s]!!.add(freeSyncCallback)
                isHas = true
            }
        }
        if (!isHas) {
            val callBackList = ArrayList<FreeSyncCallback>()
            callBackList.add(freeSyncCallback)
            freeSyncCallbackHashMap[name] = callBackList
        }
    }

    fun addCallBackOnly(name: String, freeSyncCallback: FreeSyncCallback) {
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                freeSyncCallbackHashMap.remove(s)
            }
        }
        val callBackList = ArrayList<FreeSyncCallback>()
        callBackList.add(freeSyncCallback)
        freeSyncCallbackHashMap[name] = callBackList

    }


    fun callBack(name: String, obj: Any) {
        var callBackList: ArrayList<FreeSyncCallback>?
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                callBackList = freeSyncCallbackHashMap[s]
                for (i in callBackList!!.indices) {
                    callBackList[i].onCallBack(name, obj)
                }
            }
        }
    }

    fun callBack(name: String) {
        var callBackList: ArrayList<FreeSyncCallback>?
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                callBackList = freeSyncCallbackHashMap[s]
                for (i in callBackList!!.indices) {
                    callBackList[i].onCallBack(name, "")
                }
            }
        }
    }

    fun removeCallBack(name: String) {
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                freeSyncCallbackHashMap.remove(name)
            }
        }
    }

    fun removeFreeSync(name: String) {
        for (s in freeSyncArrayMap.keys) {
            if (name == s) {
                freeSyncArrayMap.remove(name)
            }
        }
    }

    fun clearAllDefault() {
        freeSyncCallbackHashMap.clear()
    }

    fun clearAllWithName(name: String) {
        val freeSync = freeSyncWithName(name)
        freeSync?.freeSyncCallbackHashMap?.clear()

        test(CallBackTest())
    }

    private fun test(call: FreeSyncTest.CallBackTest) {

    }


    interface FreeSyncCallback {

        fun onCallBack(name: String, obj: Any)
    }

    class CallBackTest {
        private lateinit var mListener: ListenerBuilder

        fun setListener(listenerBuilder: ListenerBuilder.() -> Unit) {
            mListener = ListenerBuilder().also(listenerBuilder)
        }

        inner class ListenerBuilder {
            internal var mCallBackAction: ((String) -> String)? = null

            fun onCallBack(action: (String) -> String) {
                mCallBackAction = action
            }
        }

    }

    companion object {

        /**
         * 默认本类名存储key，自定义key避免使用此名称
         */
        private val DEFAULTFREESYNCNAME = "FREESYNC_DEFAULTFREESYNCNAME"
        private val freeSyncArrayMap = ArrayMap<String, FreeSyncTest>()
        private val freeSync = FreeSyncTest()

        fun defaultFreeSync(): FreeSyncTest {
            freeSyncArrayMap[DEFAULTFREESYNCNAME] = freeSync
            return freeSync
        }

        fun freeSyncWithName(name: String): FreeSyncTest? {
            if (freeSyncArrayMap.containsKey(name)) {
                return freeSyncArrayMap[name]
            } else {
                freeSyncArrayMap[name] = FreeSyncTest()
                return freeSyncArrayMap[name]
            }
        }
    }
}
