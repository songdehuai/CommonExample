package com.songdehuai.commonlib.task

/**
 * 任务执行者回调
 */
interface TaskCallback {

    interface CommonCallback {

        fun background()

        fun finish()

    }

}