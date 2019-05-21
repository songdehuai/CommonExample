package com.songdehuai.commonlib.base


/**
 * IDataView
 */
interface IDataView {

    /**
     * 显示Toast
     *
     * @param toast Toast内容
     */
    fun showToast(toast: String)

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


}
