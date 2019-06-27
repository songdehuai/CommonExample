package com.songdehuai.commonlib.widget.title

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.songdehuai.commonlib.R

import com.songdehuai.commonlib.widget.title.TitleType.NONE

/**
 * 标题view
 */
class TitleView : RelativeLayout {

    private var titleType = NONE
    private var finishTv: TextView? = null
    private var finishIv: ImageView? = null
    private var titleTv: TextView? = null
    private var finishLi: LinearLayout? = null
    private var rightTv: TextView? = null
    private var rightIv: ImageView? = null
    private var rightRl: RelativeLayout? = null
    private var titleCallBack: TitleCallBack? = null
    private var titleRootView: RelativeLayout? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.base_title, this)
        titleRootView = findViewById(R.id.base_title_root)
        finishTv = findViewById(R.id.title_finish_tv)
        finishIv = findViewById(R.id.title_finish_iv)
        finishLi = findViewById(R.id.title_finish_li)
        titleTv = findViewById(R.id.base_title_tv)
        rightTv = findViewById(R.id.title_right_tv)
        rightRl = findViewById(R.id.title_right_rl)
        rightIv = findViewById(R.id.title_right_iv)

        rightRl?.setOnClickListener {
            if (titleCallBack != null) {
                titleCallBack!!.onPublish()
            }
        }

        finishLi?.setOnClickListener {
            if (titleCallBack != null) {
                titleCallBack!!.onBack()
            }
        }
    }

    fun setTitleCallBack(titleType: TitleType, titleCallBack: TitleCallBack) {
        this.titleType = titleType
        this.titleCallBack = titleCallBack
        when (titleType) {
            TitleType.NONE -> {
                rightRl?.visibility = View.GONE
                finishLi?.visibility = View.GONE
            }
            TitleType.DETAIL -> {
                rightRl?.visibility = View.GONE
                finishLi?.visibility = View.VISIBLE
            }
            TitleType.PUBLISH -> {
                rightRl?.visibility = View.VISIBLE
                finishLi?.visibility = View.VISIBLE
            }
            TitleType.PUBLISH_ONE -> {
                rightRl?.visibility = View.VISIBLE
                finishLi?.visibility = View.GONE
            }
        }

    }

    fun setTitleText(titleText: CharSequence) {
        titleTv?.text = titleText
    }

    fun setPublishText(text: CharSequence) {
        rightTv?.visibility = View.VISIBLE
        rightTv?.text = text
    }


}