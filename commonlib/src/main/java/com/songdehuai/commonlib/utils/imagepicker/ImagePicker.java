package com.songdehuai.commonlib.utils.imagepicker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ImagePicker {

    private static ImagePicker imagePicker = new ImagePicker();

    private static ImagePickerCallBack imagePickerCallBack;

    public static ImagePicker getInstance() {
        return imagePicker;
    }

    public void startCameraPicker(Context context, ImagePickerCallBack imagePickerCallBack) {
        ImagePicker.imagePickerCallBack = imagePickerCallBack;
        Intent intent = new Intent(context, ImagePickerCalBackActivity.class);
        intent.putExtra("isCamera", true);
        context.startActivity(intent);
    }


    public void startImagePicker(Context context, ImagePickerCallBack imagePickerCallBack) {
        ImagePicker.imagePickerCallBack = imagePickerCallBack;
        Intent intent = new Intent(context, ImagePickerCalBackActivity.class);
        intent.putExtra("isCamera", false);
        context.startActivity(intent);
    }

    void onSuccess(String filePath) {
        if (imagePickerCallBack != null) {
            imagePickerCallBack.onGetImage(filePath);
        }
    }
}
