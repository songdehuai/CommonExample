package com.songdehuai.commonexample.ui

import android.os.Bundle
import com.songdehuai.commonexample.R
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.utils.imagepicker.ImagePicker
import com.songdehuai.commonlib.utils.imagepicker.ImagePickerCallBack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


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

    override fun onPublish() {
        super.onPublish()

        ImagePicker.startMultiImagePicker(this, 0, ImagePickerCallBack {
            it?.forEach {
                log(it.filePath)
            }
        })
    }

    private fun log(str: String) {
        log_et.append(str)
    }


}
