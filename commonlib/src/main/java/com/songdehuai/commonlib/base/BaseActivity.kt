package com.songdehuai.commonlib.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.songdehuai.commonlib.R
import com.songdehuai.commonlib.widget.title.TitleCallBack
import kotlinx.android.synthetic.main.base_activity.*


/**
 * BaseActivity
 */
open class BaseActivity<DataType> : AppCompatActivity() {

    private var data: DataType? = null
    private lateinit var mConentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
    }


    fun setContentView(layoutId: Int, title: String, callBack: TitleCallBack.DetailCallBack) {
        titleView.setTitleText(title)
        titleView.setTitleCallBack(callBack)
        setContentView(layoutId)

    }


    open fun showData(dataType: DataType) {

    }

}