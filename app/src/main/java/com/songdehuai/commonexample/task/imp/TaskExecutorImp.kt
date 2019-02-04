package com.songdehuai.commonlib.task.imp

import androidx.annotation.UiThread
import com.songdehuai.commonexample.HttpTools
import com.songdehuai.commonexample.NetworkUtil
import com.songdehuai.commonlib.task.TaskCallback
import com.songdehuai.commonlib.task.TaskExecutor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * 任务执行者实现
 */
class TaskExecutorImp : TaskExecutor {
    override fun start() {
        GlobalScope.launch {
            print(Calendar.getInstance().timeInMillis.toString())
        }.start()
    }


    override fun start(callbcck: TaskCallback.CommonCallback) {
        GlobalScope.launch {
            var uri = "http://39.97.129.31/freight-h5/#/customservice?type=2"
            callbcck.finish(NetworkUtil.doGet(uri)!!)
        }.start()
    }

}