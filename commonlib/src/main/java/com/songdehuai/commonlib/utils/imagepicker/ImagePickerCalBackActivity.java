package com.songdehuai.commonlib.utils.imagepicker;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import com.songdehuai.commonlib.utils.AppUtils;
import com.songdehuai.commonlib.utils.grantor.PermissionListener;
import com.songdehuai.commonlib.utils.grantor.PermissionsUtil;

import java.io.File;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

/**
 * 拍照选择器回调
 */
public class ImagePickerCalBackActivity extends Activity {

    private int IMAGECODE = 909;
    private int CAMERACODE = 908;
    private String imageName;
    private boolean isCamera = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();
    }

    private void start() {
        isCamera = getIntent().getBooleanExtra("isCamera", false);
        if (isCamera) {
            PermissionsUtil.requestPermission(ImagePickerCalBackActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    imageName = UUID.randomUUID().toString() + ".jpg";
                    File file = new File(getExternalCacheDir(), imageName);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(ImagePickerCalBackActivity.this, getPackageName() + ".fileProvider", file));
                    } else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    }
                    startActivityForResult(intent, CAMERACODE);
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    finish();
                }
            }, Manifest.permission.CAMERA);
        } else {
            PermissionsUtil.requestPermission(ImagePickerCalBackActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGECODE);
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    finish();
                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (requestCode == CAMERACODE) {
                ImagePicker.getInstance().onSuccess(getExternalCacheDir() + "/" + imageName);
            }
        }
        if (requestCode == IMAGECODE) {
            if (null != data && null != data.getData()) {
                ImagePicker.getInstance().onSuccess(AppUtils.getRealPathFromURI(this, data.getData()));
            }
        }
        finish();
    }
}
