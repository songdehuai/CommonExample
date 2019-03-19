package com.songdehuai.commonlib.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.songdehuai.commonlib.R
import com.songdehuai.commonlib.widget.title.TitleCallBack
import com.songdehuai.commonlib.widget.title.TitleType
import kotlinx.android.synthetic.main.base_activity.*


/**
 * BaseActivity
 */
open class BaseActivity : AppCompatActivity(), TitleCallBack {

    private lateinit var mConentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
    }

    open fun setContentView(layoutId: Int, title: String) {
        titleView.setTitleText(title)
        titleView.setTitleCallBack(TitleType.DETAIL, this)
        mConentView = View.inflate(this, layoutId, null)
        content_fl.addView(mConentView)
    }

    override fun onPublish() {

    }

    override fun onBack() {
        finish()
    }
}