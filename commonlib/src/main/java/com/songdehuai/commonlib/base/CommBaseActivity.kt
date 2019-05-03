package com.songdehuai.commonlib.base

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.songdehuai.commonlib.R
import com.songdehuai.commonlib.R.*
import com.songdehuai.commonlib.utils.DialogUtils
import com.songdehuai.commonlib.utils.imagepicker.ImageItem
import com.songdehuai.commonlib.utils.imagepicker.ImagePicker
import com.songdehuai.commonlib.utils.imagepicker.ImagePickerCallBack
import com.songdehuai.commonlib.utils.ultimatebar.StatusBar
import com.songdehuai.commonlib.widget.title.TitleCallBack
import com.songdehuai.commonlib.widget.title.TitleType
import kotlinx.android.synthetic.main.base_activity.*


/**
 * CommBaseActivity
 * @author songdehuai
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
open class CommBaseActivity : AppCompatActivity(), TitleCallBack, ImagePickerCallBack {


    private lateinit var mContentView: View
    private lateinit var titleDrawable: Drawable
    private lateinit var dialogUtils: DialogUtils
    open lateinit var thisActivity: Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.base_activity)
        init()
    }

    private fun init() {
        thisActivity = this
        dialogUtils = DialogUtils(this)
        initTitle()
    }

    /**
     * 右边按钮事件
     */
    override fun onPublish() {

    }

    /**
     * 返回按钮事件
     */
    override fun onBack() {
        finish()
    }

    /**
     * 图片选择回调
     */
    override fun onGetImage(imageItemList: MutableSet<ImageItem>?) {

    }

    /**
     * 初始化Title,默认取Drawable里bg_title
     */
    private fun initTitle() {
        this.titleDrawable = getDrawable(R.drawable.bg_title)
        StatusBar.with(this)
            .statusDark(false)
            .statusDrawable(titleDrawable)
            .statusDrawable2(titleDrawable)
            .applyNavigation(false)
            .navigationDark(false)
            .navigationDrawable(titleDrawable)
            .navigationDrawable2(titleDrawable)
            .create()
            .drawableBar()
    }


    /********************************************开放方法区域********************************************/

    /**
     * 设置ContentView，带标题和返回按钮
     * @param layoutId 布局id
     * @param title 标题
     */
    open fun setContentView(layoutId: Int, title: CharSequence) {
        titleView.setTitleText(title)
        titleView.setTitleCallBack(TitleType.DETAIL, this)
        mContentView = View.inflate(this, layoutId, null)
        content_fl.removeAllViews()
        content_fl.addView(mContentView)
    }

    /**
     * 设置ContentView，仅带标题
     * @param layoutId 布局id
     * @param title 标题
     */
    open fun setContentViewNone(layoutId: Int, title: CharSequence) {
        titleView.setTitleText(title)
        titleView.setTitleCallBack(TitleType.NONE, this)
        mContentView = View.inflate(this, layoutId, null)
        content_fl.removeAllViews()
        content_fl.addView(mContentView)
    }

    /**
     * 设置ContentView 带标题返回按钮和右边按钮
     * @param layoutId 布局id
     * @param title 标题
     * @param publishStr 右边文字
     */
    open fun setContentView(layoutId: Int, title: CharSequence, publishStr: CharSequence) {
        titleView.setTitleCallBack(TitleType.PUBLISH, this)
        titleView.setTitleText(title)
        titleView.setPublishText(publishStr)
        mContentView = View.inflate(this, layoutId, null)
        content_fl.removeAllViews()
        content_fl.addView(mContentView)
    }


    /**
     * 设置TitleView右侧按钮点击文字
     */
    open fun setTitlePublishText(text: CharSequence) {
        titleView?.setPublishText(text)
    }

    /**
     * 同时设置状态栏和标题栏Drawable
     */
    open fun setTitleBack(drawable: Drawable) {
        this.titleDrawable = drawable
        StatusBar.with(this)
            .statusDark(false)
            .statusDrawable(titleDrawable)
            .statusDrawable2(titleDrawable)
            .applyNavigation(false)
            .navigationDark(false)
            .navigationDrawable(titleDrawable)
            .navigationDrawable2(titleDrawable)
            .create()
            .drawableBar()
    }

    /**
     * 显示Toast
     *
     * @param toast Toast内容
     */
    open fun showTaost(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示LoadDialog
     *
     * @param text 内容
     */
    open fun showLoadDialog(text: String) {
        dialogUtils.showLaodDialog(text)
    }

    /**
     * 关闭 LoadDialog
     */
    open fun dismissLoadDialog() {
        dialogUtils.dismissLoadDialog()
    }

    /**
     * 显示一个Dialog
     *
     * @param text 内容
     */
    open fun showDialog(text: String) {
        dialogUtils.showDialog(text)
    }

    /**
     * 关闭 Dialog
     */
    open fun dismissDialog() {
        dialogUtils.dismissDialog()
    }

    /**
     * 启动图片选择器
     */
    open fun startImagePicker() {
        ImagePicker.getInstance().startImagePicker(this, this)
    }

    /**
     * 启动相机拍照
     */
    open fun startCamera() {
        ImagePicker.getInstance().startCameraPicker(this, this)
    }

    /**
     * 启动多图选择
     */
    open fun startMultiImagePicker() {
        ImagePicker.getInstance().startMultiImagePicker(this, this)
    }

    /**
     * 显示图片选择器，图片和相机
     */
    open fun showImagePickerDialog() {
        val items = arrayOf("相册", "相机", "多图")
        AlertDialog.Builder(this@CommBaseActivity)
            .setItems(items) { _, which ->
                when (which) {
                    0 -> {
                        startImagePicker()
                    }
                    1 -> {
                        startCamera()
                    }
                    2 -> {
                        startMultiImagePicker()
                    }
                }
            }.show()
    }


}