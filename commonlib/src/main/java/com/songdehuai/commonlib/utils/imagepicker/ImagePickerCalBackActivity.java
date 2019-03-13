package com.songdehuai.commonlib.utils.imagepicker;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;


import java.io.File;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

/**
 * 拍照选择器回调
 */
public class ImagePickerCalBackActivity extends Activity {

    private int imageCode = 909;
    private String imageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startCarmera();
    }

    private void startCarmera() {
        imageName = UUID.randomUUID().toString() + ".jpg";
        File file = new File(getExternalCacheDir(), imageName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(ImagePickerCalBackActivity.this, getPackageName() + ".provider", file));
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        startActivityForResult(intent, imageCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (requestCode == imageCode) {
                Uri uri = Uri.parse(getExternalCacheDir() + "/" + imageName);
                ImagePicker.getInstance().onSuccess(uri, getExternalCacheDir() + "/" + imageName);
                finish();
            }
        }
    }
}
