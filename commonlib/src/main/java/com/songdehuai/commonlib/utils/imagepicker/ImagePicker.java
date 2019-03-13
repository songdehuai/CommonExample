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
    public void startImagePicker(Context context, ImagePickerCallBack imagePickerCallBack) {
        this.imagePickerCallBack = imagePickerCallBack;
        Intent intent = new Intent(context, ImagePickerCalBackActivity.class);
        context.startActivity(intent);
    }

    public void onSuccess(Uri uri, String filePath) {
        if (imagePickerCallBack != null) {
            imagePickerCallBack.onSuccess(uri, filePath);
        }
    }
}
