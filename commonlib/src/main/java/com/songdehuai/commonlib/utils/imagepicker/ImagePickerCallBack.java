package com.songdehuai.commonlib.utils.imagepicker;

import java.util.Set;

public interface ImagePickerCallBack {

    void onGetImage(String filePath);

    public interface MultiImagePickerCallBack {

        void onMultiImageSuccess(Set<ImageItem> imageItemList);

    }
}
