package com.songdehuai.commonlib.task.imp

import com.songdehuai.commonlib.task.TaskCallback
import com.songdehuai.commonlib.task.TaskExecutor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            callbcck.background()
        }.start()
    }


}