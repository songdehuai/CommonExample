package com.songdehuai.commonexample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.songdehuai.commonexample.R
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.utils.filter.QuickClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好", "确定")
        initViews()
    }

    private fun initViews() {

        test_btn.setOnClickListener(object : View.OnClickListener {
            @QuickClick
            override fun onClick(v: View?) {
                Log.i("test_btn", "正常点击")
            }

        })
        status_tn.setOnClickListener {
            Log.i("ClickFilter", "正常点击")
        }
    }

    override fun onGetImage(filePath: String?) {
        super.onGetImage(filePath)

    }

    override fun onPublish() {
        super.onPublish()
        showDialog("你好你好")
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
