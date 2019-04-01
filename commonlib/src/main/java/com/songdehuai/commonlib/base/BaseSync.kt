package com.songdehuai.commonlib.base


/**
 * BaseSync
 */
interface BaseSync {

    /**
     * 显示Toast
     *
     * @param toast Toast内容
     */
    fun showTaost(toast: String)

    /**
     * 显示LoadDialog
     *
     * @param text 内容
     */
    fun showLoadDialog(text: String)

    /**
     * 关闭 LoadDialog
     */
    fun dismissLoadDialog()

    /**
     * 显示一个Dialog
     *
     * @param text 内容
     */
    fun showDialog(text: String)

    /**
     * 关闭 Dialog
     */
    fun dismissDialog()

    /**
     * 启动图片选择器
     */
    fun startImagePicker()

    /**
     * 启动相机拍照
     */
    fun startCamera()

    /**
     * 显示图片选择器，图片和相机
     */
    fun showImagePickerDialog()
}
