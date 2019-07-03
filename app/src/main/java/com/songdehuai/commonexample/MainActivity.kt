package com.songdehuai.commonexample

import android.os.Bundle
import com.songdehuai.commonlib.base.BaseActivity
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MMKV.initialize(this)

        initViews()
    }

    fun initViews() {

    }
}
