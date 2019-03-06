package com.songdehuai.commonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.songdehuai.commonlib.HTTP
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {
            log(HTTP.post("http://mtweb.dongdukeji.com/src/home/list.html?city=%E9%9D%92%E5%B2%9B&app=andorid&i=1&lat=1&lng=1&tdsourcetag=s_pcqq_aiomsg"))
        }

        status_tn.setOnClickListener {
            log_et.setText("")
        }
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
