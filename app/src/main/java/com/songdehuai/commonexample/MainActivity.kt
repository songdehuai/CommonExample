package com.songdehuai.commonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.songdehuai.commonlib.task.TaskCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {
            C.test(object : TaskCallback.CommonCallback {
                override fun success(str: String) {
                    log(str)
                }
            })
        }
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
