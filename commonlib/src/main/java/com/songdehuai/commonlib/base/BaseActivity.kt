package com.songdehuai.commonlib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.songdehuai.commonlib.R

/**
 * BaseActivity
 */
open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        init()
    }

    private fun init() {

    }
}