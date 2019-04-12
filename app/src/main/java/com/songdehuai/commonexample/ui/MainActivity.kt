package com.songdehuai.commonexample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.service.SocketService
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.utils.FreeSync
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好")
        initViews()
    }

    lateinit var freeSync: FreeSync
    private fun initViews() {
        test_btn.setOnClickListener {
            val intent = Intent(this@MainActivity,TestActivity::class.java)
            startActivity(intent)

        }
        status_tn.setOnClickListener {
            val intent = Intent(this, SocketService::class.java)
            startService(intent)
        }

        freeSync = FreeSync.freeSyncWithName(SocketService.SOCKETFREESYNCNAME)
        freeSync.addCallBack(SocketService.SOCKETFREESYNCNAME_MESSAGE,
            object : FreeSync.FreeSyncCallback {
                override fun onCallBack(name: String?, `object`: Any?) {
                    log(`object`.toString())
                }

            })

    }

    override fun onPublish() {
        super.onPublish()
        showDialog("你好你好")
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
