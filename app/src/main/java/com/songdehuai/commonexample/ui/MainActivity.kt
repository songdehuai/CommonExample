package com.songdehuai.commonexample.ui

import android.os.Bundle
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.adapter.MainListAdapter
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.utils.FreeSync
import com.songdehuai.commonlib.utils.imagepicker.ImageItem
import com.songdehuai.widget.myrefreshlayout.MyRefreshLayout
import com.songdehuai.widget.myrefreshlayout.RefreshListenerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    lateinit var freeSync: FreeSync

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好")
        initViews()
    }

    var adapter: MainListAdapter? = null

    private fun initViews() {
        adapter = MainListAdapter(thisActivity)
        log_lv.adapter = adapter
        test_btn.setOnClickListener {
            adapter?.isPass = true
            adapter?.notifyDataSetChanged()
        }
        status_tn.setOnClickListener {
            adapter?.isPass = false
            adapter?.notifyDataSetChanged()
        }
        refresh_rl.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: MyRefreshLayout?) {
                super.onRefresh(refreshLayout)
                adapter?.setList(getNewData())
                refresh_rl.finish()
            }

            override fun onLoadMore(refreshLayout: MyRefreshLayout?) {
                super.onLoadMore(refreshLayout)
                adapter?.addList(getNewData())
                refresh_rl.finish()
            }
        })
        refresh_rl.startRefresh()

    }

    fun getNewData(): ArrayList<String> {
        var list = ArrayList<String>()
        for (i in 1..10) {
            list.add(UUID.randomUUID().toString())
        }
        return list
    }

    override fun onPublish() {
        super.onPublish()
        showDialog("你好你好")
    }

    private fun log(str: String) {

    }
}
