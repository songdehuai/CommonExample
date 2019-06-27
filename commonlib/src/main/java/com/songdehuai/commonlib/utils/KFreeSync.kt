package com.songdehuai.commonlib.utils

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList


/**
 * @author songdehuai
 */
class KFreeSync private constructor() {

    private val freeSyncCallbackHashMap =
        ConcurrentHashMap<String, CopyOnWriteArrayList<FreeSyncCallback>>()

    @Synchronized
    fun addCall(name: String, freeSyncCallback: FreeSyncCallback) {
        var isHas = false
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                freeSyncCallbackHashMap[s]!!.add(freeSyncCallback)
                isHas = true
            }
        }
        if (!isHas) {
            val callBackList = CopyOnWriteArrayList<FreeSyncCallback>()
            callBackList.add(freeSyncCallback)
            freeSyncCallbackHashMap[name] = callBackList
        }
    }

    @Synchronized
    fun addOneCallOnly(name: String, freeSyncCallback: FreeSyncCallback) {
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                freeSyncCallbackHashMap.remove(s)
            }
        }
        val callBackList = CopyOnWriteArrayList<FreeSyncCallback>()
        callBackList.add(freeSyncCallback)
        freeSyncCallbackHashMap[name] = callBackList
    }


    @Synchronized
    fun call(name: String, obj: Any) {
        var callBackList: CopyOnWriteArrayList<FreeSyncCallback>?
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                callBackList = freeSyncCallbackHashMap[s]
                for (i in callBackList!!.indices) {
                    callBackList[i].onCallBack(name, obj)
                }
            }
        }
    }

    @Synchronized
    fun call(name: String) {
        var callBackList: CopyOnWriteArrayList<FreeSyncCallback>?
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                callBackList = freeSyncCallbackHashMap[s]
                for (i in callBackList!!.indices) {
                    callBackList[i].onCallBack(name, "")
                }
            }
        }
    }

    @Synchronized
    fun removeCall(name: String) {
        for (s in freeSyncCallbackHashMap.keys) {
            if (name == s) {
                freeSyncCallbackHashMap.remove(name)
            }
        }
    }

    @Synchronized
    fun removeFreeSync(name: String) {
        for (s in freeSyncArrayMap.keys) {
            if (name == s) {
                freeSyncArrayMap.remove(name)
            }
        }
    }

    @Synchronized
    fun clearAllDefault() {
        freeSyncCallbackHashMap.clear()
    }

    @Synchronized
    fun clearAllWithName(name: String) {
        val freeSync = freeSyncWithName(name)
        freeSync?.freeSyncCallbackHashMap?.clear()
    }

    interface FreeSyncCallback {

        fun onCallBack(name: String, obj: Any)
    }

    companion object {

        /**
         * 默认本类名存储key，自定义key避免使用此名称
         */
        private val DEFAULTFREESYNCNAME = "FREESYNC_DEFAULTFREESYNCNAME"
        private val freeSyncArrayMap = ConcurrentHashMap<String, KFreeSync>()
        private val freeSync = KFreeSync()

        fun defaultFreeSync(): KFreeSync {
            freeSyncArrayMap[DEFAULTFREESYNCNAME] = freeSync
            return freeSync
        }

        fun freeSyncWithName(name: String): KFreeSync? {
            if (freeSyncArrayMap.containsKey(name)) {
                return freeSyncArrayMap[name]
            } else {
                freeSyncArrayMap[name] = KFreeSync()
                return freeSyncArrayMap[name]
            }
        }
    }
}
