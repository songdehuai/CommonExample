package com.songdehuai.commonexample.ui

import android.os.Bundle

import com.songdehuai.commonexample.R
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.utils.imagepicker.ImageItem
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test, "测试")

        send_btn.setOnClickListener {
            startMultiImagePicker()
        }

    }

    override fun onMultiImageSuccess(imageItemList: MutableSet<ImageItem>?) {
        super.onMultiImageSuccess(imageItemList)
        test_et.setText("")
        imageItemList?.forEach {
            test_et.append(it.filePath)
        }

    }

}
