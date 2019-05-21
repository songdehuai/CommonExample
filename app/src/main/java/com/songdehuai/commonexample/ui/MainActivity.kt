package com.songdehuai.commonexample.ui

import android.content.Intent
import android.os.Bundle
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.adapter.KTAdapterTest
import com.songdehuai.commonlib.base.CommBaseActivity
import com.songdehuai.commonlib.utils.FreeSync
import com.songdehuai.commonlib.utils.imagepicker.ImageItem
import com.songdehuai.commonlib.ws.CommSocketClient
import com.songdehuai.commonlib.ws.MessageSender
import com.songdehuai.commonlib.ws.SocketService
import com.songdehuai.widget.myrefreshlayout.MyRefreshLayout
import com.songdehuai.widget.myrefreshlayout.RefreshListenerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : CommBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好", "图库")
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {

        }
        status_tn.setOnClickListener {


        }

    }

    override fun onGetImage(imageItemList: MutableSet<ImageItem>?) {
        super.onGetImage(imageItemList)
        imageItemList?.forEach {
            log(it.filePath)
        }
    }

    override fun onPublish() {
        super.onPublish()
        showImagePickerDialog()
    }


    private fun log(str: String) {
        log_et.append(str)
    }


}
