package com.songdehuai.commonlib.widget.title

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.songdehuai.commonlib.R

/**
 * 标题控件
 */
class TitleView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :

    RelativeLayout(context, attrs, defStyleAttr) {

    private var titleType = TitleType.NONE
    private var finishTv: TextView? = null
    private var finishIv: ImageView? = null
    private var titleTv: TextView? = null
    private var finishLi: LinearLayout? = null
    private var rightTv: TextView? = null
    private var rightIv: ImageView? = null
    private var rightRl: RelativeLayout? = null

    private var titleCallBack: TitleCallBack? = null

    constructor(context: Context) : this(context, null) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initViews()
    }

    init {
        initViews()
    }


    private fun initViews() {
        View.inflate(context, R.layout.view_title, this)
        finishTv = findViewById(R.id.title_finish_tv)
        finishIv = findViewById(R.id.title_finish_iv)
        finishLi = findViewById(R.id.title_finish_li)
        titleTv = findViewById(R.id.title_tv)
        rightTv = findViewById(R.id.title_right_tv)
        rightRl = findViewById(R.id.title_right_rl)
        rightIv = findViewById(R.id.title_right_iv)

        rightRl?.visibility = View.GONE

        rightRl?.setOnClickListener {
            titleCallBack?.onPublish()
        }
        finishLi?.setOnClickListener {
            titleCallBack?.onBack()
        }
    }

    fun setTitleCallBack(titleType: TitleType, titleCallBack: TitleCallBack) {
        this.titleType = titleType
        this.titleCallBack = titleCallBack
        when (titleType) {
            TitleType.NONE -> {
                rightRl?.visibility = View.INVISIBLE
                finishLi?.visibility = View.INVISIBLE
            }
            TitleType.DETAIL -> {
                rightRl?.visibility = View.INVISIBLE
            }
            TitleType.PUBLISH -> {
                rightRl?.visibility = VISIBLE
            }
        }
    }


    fun setTitleText(titleText: String) {
        titleTv?.text = titleText
    }


}
