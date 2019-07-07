package com.songdehuai.commonexample

import android.os.Bundle
import android.view.View
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.net.HTTP
import com.songdehuai.commonlib.net.Result
import com.songdehuai.commonlib.net.ResultCallBack
import com.songdehuai.commonlib.utils.imagepicker.ImagePicker
import com.songdehuai.commonlib.utils.imagepicker.ImagePickerCallBack
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*
import okgo.OkGo
import okgo.model.Response

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "", "相册")
        MMKV.initialize(this)

        initViews()

    }

    override fun onPublish() {
        super.onPublish()
        ImagePicker.showImagePickerDialog(this, ImagePickerCallBack {
            showDialog(it[0].filePath)
        })
    }

    fun initViews() {
        judge_btn.setOnClickListener {
            HTTP.post<String>("").execute {
                onSuccess {
                    showDialog(it)
                }
            }
        }
    }
}
