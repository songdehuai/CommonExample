package com.songdehuai.commlib.utils

import android.content.Context
import java.util.*


/**
 * 为所欲为
 * @author songdehuai
 */
object FreeSync {

    private var callBackSequences = WeakHashMap<String, MutableList<FreeSyncCallBack>>()

    /**
     * 添加callBack
     */
    fun addCallBack(name: String, callBack: FreeSyncCallBack) {
        var isHas = false
        callBackSequences.forEach {
            if (it.key == name) {
                callBackSequences[it.key]?.add(callBack)
                isHas = true
            }
        }
        //不存在创建添加
        if (!isHas) {
            val selList = ArrayList<FreeSyncCallBack>()
            selList.add(callBack)
            callBackSequences[name] = selList
        }
    }


    /**
     * callBack
     */
    fun callBack(name: String, any: Any) {
        callBackSequences.forEach {
            if (it.key == name) {
                val callBacks = callBackSequences[it.key]
                callBacks?.forEach { callBack ->
                    callBack.onCallBack(name, any)
                }
            }
        }
    }

    /**
     * 移除callBack
     */
    fun remove(vararg names: String) {
        names.forEach {
            callBackSequences.remove(it)
        }
    }

    /**
     * 移除callBack
     */
    fun remove(name: String) {
        callBackSequences.remove(name)
    }

    /**
     * 清除所有callBack
     */
    fun removeAll() {
        callBackSequences.clear()
    }

    /**
     * callBack
     */
    fun callBack(name: String) {
        callBack(name, "")
    }

    /**
     * callBack
     */
    fun callBacks(vararg names: String) {
        names.forEach {
            callBack(it, "")
        }
    }


    /**
     * callBack in Ui Thread
     */
    fun callBackUi(context: Context, name: String) {
        context.mainLooper.run {
            callBack(name, "")
        }
    }

    /**
     * callBack in Ui Thread
     */
    fun callBacksUi(context: Context, vararg names: String) {
        names.forEach {
            context.mainLooper.run {
                callBack(it, "")
            }
        }
    }


    /**
     * FreeSync CallBack
     */
    interface FreeSyncCallBack {
        fun onCallBack(name: String, any: Any)
    }
}
