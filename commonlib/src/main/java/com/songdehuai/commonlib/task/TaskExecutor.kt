package com.songdehuai.commonlib.task

/**
 * 异步任务执行者
 */
interface TaskExecutor {

    /**
     * 执行任务
     */
    fun  start()

    /**
     * 执行任务
     */
    fun  start(callbcck: TaskCallback.CommonCallback)

}
