package com.songdehuai.commonexample.ui

import android.content.Intent
import android.os.Bundle
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.adapter.KAdapterTest
import com.songdehuai.commonexample.ui.adapter.MainListAdapter
import com.songdehuai.commonlib.base.CommBaseActivity
import com.songdehuai.commonlib.utils.FreeSync
import com.songdehuai.commonlib.utils.LogUtil
import com.songdehuai.commonlib.ws.CommSocketClient
import com.songdehuai.commonlib.ws.SocketService
import com.songdehuai.commonlib.wsmanager.WsManager
import com.songdehuai.widget.myrefreshlayout.MyRefreshLayout
import com.songdehuai.widget.myrefreshlayout.RefreshListenerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivityComm : CommBaseActivity() {

    lateinit var freeSync: FreeSync

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好", "图库")
        initViews()
    }

    private fun initViews() {
        val adapter = KAdapterTest
        log_lv.adapter = adapter

        test_btn.setOnClickListener {
            //CommSocketClient.connect(thisActivity)
            val intent = Intent(this, SocketService::class.java)
            startService(intent)
        }
        status_tn.setOnClickListener {
            val temp = UUID.randomUUID().toString();
            LogUtil.i("发送：$temp")
            CommSocketClient.sendMsg(temp)
        }

        refresh_rl.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: MyRefreshLayout?) {
                super.onRefresh(refreshLayout)
                adapter.data { getNewData() }
                refresh_rl.finish()
            }

            override fun onLoadMore(refreshLayout: MyRefreshLayout?) {
                super.onLoadMore(refreshLayout)
                adapter.addDatas(getNewData())
                refresh_rl.finish()
            }
        })
        refresh_rl.startRefresh()


    }

    fun getNewData(): ArrayList<Myentity> {
        val list = ArrayList<Myentity>()
        for (i in 1..10) {
            list.add(Myentity(UUID.randomUUID().toString(), false))
        }
        return list
    }

    override fun onPublish() {
        super.onPublish()

    }


    private fun log(str: String) {
        showImagePickerDialog()
    }


}
