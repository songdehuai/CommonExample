package com.songdehuai.commonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.songdehuai.commonlib.task.TaskCallback
import com.songdehuai.commonlib.task.TaskExecutor
import com.songdehuai.commonlib.task.imp.TaskExecutorImp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {
            var task: TaskExecutor
            task = TaskExecutorImp()
            task.start(object : TaskCallback.CommonCallback {
                override fun background() {

                }

                override fun finish(str: String) {
                    log(str)
                }

            })
        }
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
