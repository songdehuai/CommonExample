package com.songdehuai.commonexample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.songdehuai.commonexample.R
import com.songdehuai.commonlib.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好")
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener { Log.i("test_btn", "正常点击") }
        status_tn.setOnClickListener {
            val intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPublish() {
        super.onPublish()
        showDialog("你好你好")
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
