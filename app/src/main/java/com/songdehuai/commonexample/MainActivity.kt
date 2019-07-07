package com.songdehuai.commonexample

import android.os.Bundle
import android.view.View
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.net.*
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*
import okgo.OkGo
import okgo.model.Response

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MMKV.initialize(this)

        initViews()
    }

    val url = "http://94.191.9.222:8080/pet/test/getAllUrl"

    fun initViews() {

        judge_btn.setOnClickListener {

            HTTP.post<String>(url) {
                success {

                }
                error {
                    showDialog("错误")
                }
            }

//            OkGo.post<Result<List<TestData>>>(url)
//                .execute(object : ResultCallBack<Result<List<TestData>>>() {
//                    override fun onSuccess(response: Response<Result<List<TestData>>>?) {
//                        super.onSuccess(response)
//                        response?.body()?.message?.let { it1 -> showDialog(it1) }
//                    }
//
//                    override fun onError(response: Response<Result<List<TestData>>>?) {
//                        super.onError(response)
//
//                    }
//                })
        }
    }
}
