package com.songdehuai.commonlib.base;


/**
 * BaseSync
 */
public interface BaseSync {

    /**
     * 显示Toast
     *
     * @param toast Toast内容
     */
    void showTaost(String toast);

    /**
     * 显示LoadDialog
     *
     * @param text 内容
     */
    void showLoadDialog(String text);

    /**
     * 关闭 LoadDialog
     */
    void dismissLoadDialog();

    /**
     * 显示一个Dialog
     *
     * @param text 内容
     */
    void showDialog(String text);

    /**
     * 关闭 Dialog
     */
    void dismissDialog();

    /**
     * 启动图片选择器
     */
    void startImagePicker();

    /**
     * 启动相机拍照
     */
    void startCamera();

    /**
     * 显示图片选择器，图片和相机
     */
    void showImagePickerDialog();
}
